# 系统部署与外部集成 (System Deployment & Integration)

本文档通过部署图展示“慧知开源充电桩平台”的物理架构与外部系统交互边界，帮助理解“系统是如何连接外部世界的”。

## 1. 总体部署架构 (Architecture Overview)

系统采用 **SaaS 云端微服务架构**，通过标准协议与硬件设备、支付渠道及监管平台进行数据交换。

```mermaid
flowchart TD
    %% 样式
    classDef cloud fill:#e3f2fd,stroke:#1565c0,stroke-width:2px;
    classDef hardware fill:#fff3e0,stroke:#e65100,stroke-width:2px;
    classDef ext fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px;
    classDef user fill:#fff9c4,stroke:#fbc02d,stroke-width:2px;

    %% 用户端
    subgraph UserEnd [用户触达层]
        direction TB
        App[微信/支付宝小程序]:::user
        AdminWeb[Web管理后台]:::user
    end

    %% 云端核心
    subgraph CloudCore [SaaS 云平台-微服务]
        direction TB
        ApiGateway[API 网关]:::cloud
        AuthService[认证中心]:::cloud
        OrderService[订单中心]:::cloud
        DeviceService[设备管理中心]:::cloud
        SettlementService[计费与结算中心]:::cloud

        ApiGateway --> AuthService
        ApiGateway --> OrderService
        ApiGateway --> DeviceService
        ApiGateway --> SettlementService
    end

    %% 硬件连接层
    subgraph IoTLayer [硬件接入层]
        direction TB
        NettyGateway[TCP 长连接网关]:::cloud
        ProtocolParser[协议解析器]:::cloud
        Mq[消息队列-RabbitMQ-Kafka]:::cloud

        NettyGateway <--> ProtocolParser
        ProtocolParser <--> Mq
        Mq <--> DeviceService
    end

    %% 外部系统
    subgraph External [外部集成系统]
        direction TB
        WeChatPay[微信支付-API]:::ext
        AliPay[支付宝-API]:::ext
        GovPlatform[政府监管平台-互联互通]:::ext
        SmsProvider[短信服务商]:::ext
    end

    %% 硬件设备
    subgraph Hardware [终端设备]
        direction TB
        PileA[直流快充桩]:::hardware
        PileB[交流慢充桩]:::hardware
    end

    %% 连线关系
    UserEnd --> ApiGateway

    OrderService --> WeChatPay
    OrderService --> AliPay

    DeviceService --> GovPlatform

    AuthService --> SmsProvider

    Hardware <--> NettyGateway

    %% 注释
    GatewayNote[处理二进制协议-如云快充1.6<br/>维持百万级设备长连接]
    NettyGateway -.-> GatewayNote
    style GatewayNote fill:#fff,stroke:#333,stroke-dasharray: 5 5
```

## 2. 外部集成详解 (Integration Details)

### 2.1 硬件通信集成 (Hardware Gateway)
-   **协议标准**：通常遵循 **云快充 V1.5/V1.6** 或 **国标 104 规约**。
-   **通信方式**：TCP 长连接 (Keep-Alive)。端口通常为 `8768`。
-   **核心职责**：
    -   **心跳维持**：定期发送 `Heartbeat` 包，确保设备在线。
    -   **指令下发**：将 JSON 格式的业务指令（如“启动充电”）转换为二进制字节流发送给设备。
    -   **数据解析**：将设备上报的二进制数据（电压、电流、错误码）解析为 JSON 并推送到 MQ。

### 2.2 支付渠道集成 (Payment Gateway)
-   **微信/支付宝**：
    -   **统一下单**：生成预支付订单，返回 `prepay_id` 给小程序端调起支付。
    -   **分账 (Profit Sharing)**：支持将资金按比例分账给具体的运营商（租户）账户，满足合规要求。
    -   **退款**：处理充电失败或预充值余额的退款申请。

### 2.3 监管平台对接 (Regulatory Integration)
-   **互联互通 (Hlht)**：
    -   **数据推送**：根据中电联（CEC）标准，定时将站点基础信息、设备状态、实时订单流水推送至省/市级监管平台。
    -   **主要接口**：
        -   `query_stations_info`: 站点查询
        -   `query_station_status`: 状态查询
        -   `notification_start_charge_result`: 启动结果推送
        -   `notification_stop_charge_result`: 停止结果推送
