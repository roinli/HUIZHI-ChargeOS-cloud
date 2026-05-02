# 核心业务流 (Core Business Flow)

本文档通过详细的时序图与流程图，深度剖析“扫码充电”这一核心业务场景的全生命周期。

## 1. 扫码充电业务全景图 (Overview Flowchart)

用户从扫码到充电结束，经历了三个主要阶段：**准备阶段**、**充电阶段**、**结算阶段**。

```mermaid
flowchart TD
    %% 样式
    classDef start fill:#f9fbe7,stroke:#827717,stroke-width:2px;
    classDef process fill:#e3f2fd,stroke:#1565c0,stroke-width:2px;
    classDef decision fill:#fff3e0,stroke:#e65100,stroke-width:2px;
    classDef endNode fill:#ffebee,stroke:#c62828,stroke-width:2px;

    Start[用户扫码]:::start
    CheckGun{检测枪头状态}:::decision
    Auth{鉴权与余额检查}:::decision
    SendCmd[下发启动指令]:::process
    PileAck{桩端响应}:::decision
    Charging[充电进行中]:::process
    Monitor{实时监测}:::decision
    StopCmd[用户/系统发起停止]:::process
    Settlement[生成账单与扣款]:::process
    End[结束流程]:::endNode

    Start --> CheckGun
    CheckGun -- 未插枪 --> PromptInsert[提示插入枪头]:::process
    PromptInsert --> CheckGun
    CheckGun -- 已插枪 --> Auth

    Auth -- 余额不足 --> PromptRecharge[提示充值]:::process
    PromptRecharge --> Auth
    Auth -- 通过 --> SendCmd

    SendCmd --> PileAck
    PileAck -- 失败-超时 --> FailMsg[提示启动失败]:::endNode
    PileAck -- 成功 --> Charging

    Charging --> Monitor
    Monitor -- 正常 --> Monitor
    Monitor -- 异常-急停 --> StopCmd
    Monitor -- 用户手动停止 --> StopCmd
    Monitor -- 余额耗尽 --> StopCmd

    StopCmd --> Settlement
    Settlement --> End
```

## 2. 详细时序交互 (Detailed Sequence)

### 2.1 正向流程：启动与结算 (Start & Settlement)

```mermaid
sequenceDiagram
    autonumber
    actor User as 车主-小程序
    participant Cloud as 云平台-SaaS
    participant Gateway as 硬件网关-TCP
    participant Pile as 充电桩-设备

    Note over User, Pile: 阶段一：启动充电
    User->>Cloud: 扫码请求-桩ID
    Cloud->>Cloud: 校验桩状态-空闲-故障
    Cloud->>Cloud: 校验用户余额-信用分
    Cloud->>Gateway: 发送启动指令-用户ID-金额限制
    Gateway->>Pile: 转发启动报文-二进制协议
    Pile-->>Gateway: 回复启动确认-ACK
    Gateway-->>Cloud: 启动成功通知
    Cloud-->>User: 提示“启动成功，准备充电”

    Note over Pile: 设备自检绝缘性...

    Note over User, Pile: 阶段二：充电进行中
    loop 每15-60秒上报一次
        Pile->>Gateway: 实时数据-电压-电流-SOC-消费金额
        Gateway->>Cloud: 数据解析与存储
        Cloud->>Cloud: 更新订单实时费用
        Cloud->>User: 推送充电进度-Socket-轮询
    end

    Note over User, Pile: 阶段三：结束与结算
    User->>Cloud: 点击“停止充电”
    Cloud->>Gateway: 发送停止指令
    Gateway->>Pile: 转发停止报文
    Pile->>Pile: 停止输出，锁定账单
    Pile-->>Gateway: 上报最终结算数据-总电量-总金额-停止原因
    Gateway-->>Cloud: 转发结算数据
    Cloud->>Cloud: 生成最终订单
    Cloud->>Cloud: 扣除余额/发起支付
    Cloud-->>User: 展示账单详情
```

### 2.2 逆向流程：启动失败 (Start Failure)

当指令下发后，设备可能因故障、网络超时或物理连接问题无法启动。

```mermaid
sequenceDiagram
    participant User as 车主
    participant Cloud as 云平台
    participant Gateway as 硬件网关
    participant Pile as 充电桩

    User->>Cloud: 请求启动充电
    Cloud->>Gateway: 下发启动指令
    Gateway->>Pile: 转发指令

    alt 场景A：设备无响应-超时
        Gateway--xPile: 网络超时
        Gateway-->>Cloud: 响应超时-Timeout
        Cloud-->>User: 提示“设备连接超时，请重试”
    else 场景B：设备拒绝-故障
        Pile-->>Gateway: 回复失败-错误码-绝缘检测异常
        Gateway-->>Cloud: 启动失败-绝缘异常
        Cloud-->>User: 提示“设备故障，请更换桩”
        Cloud->>Cloud: 自动发起退款-若已预冻结
    end
```

## 3. 关键业务规则 (Business Rules)

### 3.1 资金冻结与扣款
为防止“逃单”风险，通常采用两种策略：
1.  **预充值/预冻结**：启动前冻结用户账户一定金额（如30元）。充电结束时多退少补。
    -   *风险*：用户体验较差。
2.  **信用支付**：引入微信支付分或支付宝芝麻信用，先充后付。
    -   *风险*：需接入第三方信用体系。

### 3.2 异常停止策略
当出现以下情况时，云平台必须**强制下发停止指令**：
1.  **余额耗尽**：实时计费金额 >= 账户余额。
2.  **设备离线**：长时间（如5分钟）未收到设备心跳或实时数据。
3.  **运营干预**：管理员在后台手动停止。

### 3.3 掉单处理 (Offline Billing)
若充电结束时网络中断，桩端必须保存充电记录。待网络恢复后，通过“补单协议”上报云端，云端根据补单数据完成延迟结算。
