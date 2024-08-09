package com.jingli.inter.utils;

import java.util.Random;


public class RandomUtil {



    /**
     *@Description: 生成制定位置随机数字
     *@Param: length
     *@return: 字符串
     *@Author: liwenhui
     *@date:
     */
    public static String getRandomNumber(int length) {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
