package com.lytech.xvjialing.common.utils;

import android.content.Context;

import com.lytech.xvjialing.common.conf.CommonStr;

/**
 * Created by xvjialing on 2017/11/6.
 */

public class SPDataUtils {

    private SPDataUtils(){}

    public static synchronized SPDataUtils getInstance(){
        return SPDataUtilsHolder.sPDataUtils;
    }

    private static class SPDataUtilsHolder{
        private static final SPDataUtils sPDataUtils=new SPDataUtils();
    }

    public static void setTeacher(Context context,String teacher){
        SharedPreferencesUtils.setParam(context, CommonStr.TEACHER,teacher);
    }

    public static String getTeacher(Context context){
        return SharedPreferencesUtils.getParam(context,CommonStr.TEACHER,"{}").toString();
    }


    public static void setStudent(Context context,String student){
        SharedPreferencesUtils.setParam(context, CommonStr.STUDENT,student);
    }

    public static String getStudent(Context context){
        return SharedPreferencesUtils.getParam(context,CommonStr.STUDENT,"{}").toString();
    }
}
