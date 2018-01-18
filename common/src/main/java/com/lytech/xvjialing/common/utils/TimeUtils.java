package com.lytech.xvjialing.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xvjialing on 2018/1/12.
 */

public class TimeUtils {

    public static String getTime(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }

    public static Date tranforDate(String timeStamp) throws ParseException {
        long time= Long.parseLong(timeStamp)*1000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d=sdf.format(time);
        return sdf.parse(d);
    }

    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(long timestamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        res = simpleDateFormat.format(date);
        return res;
    }
}
