package com.glg.baselib.net;



import com.glg.baselib.BuildConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具基类
 * 复写getBaseUrl返回对应域名
 *
 */
public abstract class BaseHttpMethods {


    /**
     * 获取域名
     */
    protected abstract String getBaseUrl();




    /**
     * 获取网络请求工具
     * @return
     */
    protected Retrofit getRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).
                        writeTimeout(15, TimeUnit.SECONDS);
//        if (BuildConfig.DEBUG){
//            builder.addInterceptor(new OkHttpLoggingInterceptor());
//        }

        builder.addInterceptor(new OkHttpLoggingInterceptor());
        OkHttpClient httpClient =  builder.build();


        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .client(httpClient)
                .build();
    }


    /**
     * 获取网络请求工具,该方法用于返回参数非json格式，
     * 例如返回参数加密了需要解密成json
     * @return
     */
    protected Retrofit getRetrofit(Converter.Factory factory){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).
                        writeTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG){
            builder.addInterceptor(new OkHttpLoggingInterceptor());
        }

        OkHttpClient httpClient =  builder.build();


        return new Retrofit.Builder()
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .client(httpClient)
                .build();
    }






}