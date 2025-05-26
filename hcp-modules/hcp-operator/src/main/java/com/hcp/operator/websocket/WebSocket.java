package com.hcp.operator.websocket;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/charge/{orderNumber}")
public class WebSocket {

    private static final Map<String, WebSocket> CLIENTS = new ConcurrentHashMap<>();
    private Session session;
    private String orderNumber;

//    @Autowired
//    private OrderMapper orderMapper;

    /**
     * 连接成功
     * @param orderNumber 自定义参数
     * @param session 会话信息
     */
    @OnOpen
    public void onOpen(@PathParam("orderNumber") String orderNumber, Session session) {
        log.info("订单socket链接成功:{}",orderNumber);
        this.orderNumber = orderNumber;
        this.session = session;
        CLIENTS.put(this.orderNumber, this);
//        ChargingOrder order = orderMapper.getOrderByOrderNumber(orderNumber);
//        String orderState = order.getOrderstate();
//        MessageDto messageDto  = MessageDto.builder().hasChargePower(BigDecimal.ZERO).statusCode(Short.valueOf(orderState))
//                .totalFee(BigDecimal.ZERO).serviceFee(BigDecimal.ZERO).powerFee(BigDecimal.ZERO).startTime(order.getStarttime())
//                .soc(0).realTimePower(0).portName(order.getPortName()).build();
//        sendMessageToOrder(orderNumber, JSONUtil.toJsonStr(messageDto));
//        log.info("订单socket链接成功:{}",orderNumber);
    }

    /**
     * 连接关闭
     *
     */
    @OnClose
    public void onClose() {
        CLIENTS.remove(orderNumber);
        log.info("订单socket连接关闭:{}",orderNumber);
    }

    @OnMessage
    public void onMessage(String message,Session session) {
        sendMessage("批量回复:" + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket发生错误：" + throwable.getMessage());
    }

    public static void sendMessage(String message) {
        // 向所有连接websocket的客户端发送消息
        // 可以修改为对某个客户端发消息
        for (WebSocket item : CLIENTS.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发送订单的实时消息
     * @param orderNumber 订单编号
     * @param message 消息体，json字符串
     */
    public static void sendMessageToOrder(String orderNumber,String message) {
        WebSocket socket = CLIENTS.get(orderNumber);
        if(ObjectUtil.isNotNull(socket) && socket.session.isOpen()){
            socket.session.getAsyncRemote().sendText(message);
        }
    }
}
