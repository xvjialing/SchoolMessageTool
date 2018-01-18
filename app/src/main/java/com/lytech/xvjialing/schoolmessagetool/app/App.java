package com.lytech.xvjialing.schoolmessagetool.app;

import android.app.Application;

import com.tencent.bugly.Bugly;

/**
 * Created by xvjialing on 2018/1/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "094e6042b3", false);
    }
}
