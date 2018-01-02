package com.lytech.xvjialing.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class ToastUtils {
    public static void showMessage(String msg, Context context){
        Toast.makeText(context.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
