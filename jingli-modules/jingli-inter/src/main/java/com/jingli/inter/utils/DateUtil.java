package com.jingli.inter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author YangFei
 * @version V1.0
 * @Title: DateUtil.java
 * @Package com.yf.test
 */
public class DateUtil {


    /**
      * @MethodName: getFfffMmDdhms
      * @Description: 格式化时间为 yyyy-MM-dd HH:mm:ss
      * @Param: [date]
      * @Return: java.lang.String
      * @Author: liwenhui
      * @Date: 2019/9/5
      **/
    public static String getFfffMmDdhms(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if (date == null) {
            return "";
        }
        return fmt.format(date);
    }









    /**
      * 功能描述：时间相加
      * @param nowTime 当前时间
      * @param hour 小时
      * @return java.lang.String
      * @Author: guoliangbo
      * @Date: 2019/9/5 11:42
      */
    public static String getTimeSum(String nowTime, String hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        System.out.println(sdf.format(ca.getTime()));
        return sdf.format(ca.getTime());
    }








    /**
     * 比对时间差距
     * @param endDate 结束时间
     * @param nowDate 开始时间
     * @return 时间长度
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        StringBuilder result = new StringBuilder();
        if(day !=0 ){
            result.append(day+"天 ");
        }
        if(hour!=0){
            result.append(hour + "小时");
        }
        if(min!=0){
            result.append( min + "分钟" );
        }
        if(sec!=0){
            result.append( sec + "秒" );
        }
        return result.toString();
    }




}
