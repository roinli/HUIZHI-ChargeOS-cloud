package com.jingli.admin.util;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author lizi
 * @Description 封装各种生成唯一ID算法的工具类
 * @Version 1.0
 */
@Slf4j
public class IdGen {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return "_" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成订单编号
     *
     * @param pileNo 桩编号
     * @param gunNo  枪号
     * @return 订单编号
     */
    public static String orderId(String pileNo, byte gunNo) {
        String orderId = RandomUtil.randomNumbers(4) + pileNo + gunNo + System.currentTimeMillis();
        log.info(orderId);
        return orderId;
    }

    // 生成订单编号的方法
    public static String generateOrderNumber(String inUserName) {
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis();

        // 格式化时间戳为指定格式的字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestampStr = dateFormat.format(new Date(timestamp));

        String randomNumber = RandomUtil.randomNumbers(6);

        // 拼接订单编号
        String orderNumber = "URF" + timestampStr + randomNumber + inUserName.toUpperCase();

        return orderNumber;
    }

}
