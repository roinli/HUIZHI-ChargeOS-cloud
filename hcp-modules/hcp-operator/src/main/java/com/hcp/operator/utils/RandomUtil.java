package com.hcp.operator.utils;

import java.util.Random;


public class RandomUtil {

    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    /**
     * @MethodName: randomPassword
     * @Description: 生成随机密码
     * @Param: []
     * @Return: java.lang.String
    **/
    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(System.currentTimeMillis());
        boolean flag = false;
        //输出几位密码长度  这里是有可能8 ，9 ，10 位
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

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
