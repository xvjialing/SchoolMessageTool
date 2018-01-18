package com.lytech.xvjialing.network;

import com.lytech.xvjialing.common.conf.UrlUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class NetUtils {

    private Retrofit retrofit;
    private NetService netService;

    private static class NetUtilsHolder{
        private static final NetUtils netUtils=new NetUtils();
    }

    public static synchronized NetUtils getInstance(){
        return NetUtilsHolder.netUtils;
    }

    public NetService getNetService(){
        retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(UrlUtils.baseUrl2)
                .build();
        netService=retrofit.create(NetService.class);
        return netService;
    }
}
