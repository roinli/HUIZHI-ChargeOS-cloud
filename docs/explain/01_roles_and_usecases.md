# 用户角色与用例 (Roles & Use Cases)

本文档旨在梳理“慧知开源充电桩平台”的核心参与者及其关键业务诉求。通过可视化图表，帮助产品经理快速理解“谁在使用系统”以及“他们要做什么”。

## 1. 核心角色概览 (Role Overview)

系统采用 **SaaS 多租户架构**，核心角色分为三层：平台运营层、租户运营层、终端使用层。此外，还涉及硬件设备与外部监管方。

```mermaid
graph TD
    %% 定义样式
    classDef platform fill:#e1f5fe,stroke:#01579b,stroke-width:2px;
    classDef tenant fill:#fff9c4,stroke:#fbc02d,stroke-width:2px;
    classDef user fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px;
    classDef device fill:#f3e5f5,stroke:#7b1fa2,stroke-width:2px;

    %% 节点定义
    PlatformAdmin[平台超级管理员]:::platform
    TenantAdmin[租户/运营商管理员]:::tenant
    StationManager[站长/运维人员]:::tenant
    CarOwner[电动车主/C端用户]:::user
    ChargingPile[充电桩设备]:::device
    Regulator[政府监管平台]:::platform

    %% 关系连线
    PlatformAdmin -->|监管与结算| TenantAdmin
    TenantAdmin -->|管理与排班| StationManager
    StationManager -->|维护与巡检| ChargingPile
    CarOwner -->|扫码充电与支付| ChargingPile
    ChargingPile -->|上报数据| Regulator
```

## 2. 角色用例详解 (Detailed Use Cases)

### 2.1 平台运营方 (Platform Admin)
**定位**：SaaS 平台的拥有者，负责技术底座维护、全局资金归集、以及对租户（运营商）的监管。

```mermaid
flowchart LR
    %% 角色
    Admin[平台超级管理员]

    %% 功能模块
    subgraph CoreFunctions [核心职能]
        direction TB
        TenantMgmt[租户开户与套餐管理]
        GlobalMonitor[全网设备状态监控]
        ProtocolConfig[硬件协议与网关配置]
        Settlement[平台资金清算与分账]
    end

    %% 连线
    Admin --> TenantMgmt
    Admin --> GlobalMonitor
    Admin --> ProtocolConfig
    Admin --> Settlement
```

### 2.2 租户/运营商 (Tenant Operator)
**定位**：具体的充电站经营者（如某物业公司、某公交公司），拥有自己的充电桩资产，自负盈亏。

- **核心诉求**：
    1.  **灵活定价**：支持分时电价（尖峰平谷）、会员价、阶梯电价。
    2.  **营收可视**：实时查看今日收入、订单详情。
    3.  **设备管理**：远程重启、下发升级、查看故障。

```mermaid
flowchart LR
    %% 角色
    Tenant[租户管理员]

    %% 功能模块
    subgraph OpsFunctions [运营职能]
        direction TB
        StationMgmt[站点与桩群管理]
        PriceStrategy[电价与服务费模板配置]
        OrderQuery[充电订单查询与退款]
        RevenueReport[营收报表与财务对账]
        CouponMgmt[优惠券与会员营销]
    end

    %% 连线
    Tenant --> StationMgmt
    Tenant --> PriceStrategy
    Tenant --> OrderQuery
    Tenant --> RevenueReport
    Tenant --> CouponMgmt
```

### 2.3 终端用户/车主 (End User)
**定位**：使用微信/支付宝小程序进行充电的C端用户。

```mermaid
flowchart LR
    %% 角色
    User[电动车主]

    %% 功能模块
    subgraph AppFunctions [小程序功能]
        direction TB
        FindStation[地图找桩与导航]
        ScanCharge[扫码启动充电]
        MyWallet[充值与余额查询]
        OrderHistory[历史订单与开票]
        StopCharge[远程停止充电]
    end

    %% 连线
    User --> FindStation
    User --> ScanCharge
    User --> MyWallet
    User --> OrderHistory
    User --> StopCharge
```

### 2.4 充电桩硬件 (Charging Pile)
**定位**：执行指令的哑终端（Dumb Terminal），需保持与云端的长连接。

- **关键行为**：
    -   **心跳 (Heartbeat)**：每隔 N 秒向云端报活。
    -   **鉴权 (Auth)**：刷卡或扫码时请求云端鉴权。
    -   **遥测 (Telemetry)**：充电中实时上报电压、电流、功率。
    -   **告警 (Alarm)**：急停按下、过温、过压时立即上报。

---

## 3. 业务协同总览 (Collaboration Overview)

各角色在一次完整的“充电服务”中的协同关系如下：

```mermaid
sequenceDiagram
    participant User as 车主
    participant Tenant as 运营商
    participant Platform as SaaS平台
    participant Device as 充电桩

    Note over Tenant, Platform: 1. 运营商在后台配置站点与电价
    Tenant->>Platform: 配置费率模板[尖峰平谷]
    Platform->>Device: 下发费率策略-可选

    Note over User, Device: 2. 车主到站充电
    User->>Device: 插枪并扫码
    User->>Platform: 预支付/充值
    Platform->>Device: 下发启动指令
    Device-->>User: 开始充电-屏幕指示灯变化

    loop 充电中
        Device->>Platform: 实时上报-电量金额
        Platform->>User: 小程序展示进度
    end

    User->>Platform: 点击停止充电
    Platform->>Device: 下发停止指令
    Device->>Platform: 上送最终账单(消费电量)
    Platform->>User: 结算扣款并生成订单
```
