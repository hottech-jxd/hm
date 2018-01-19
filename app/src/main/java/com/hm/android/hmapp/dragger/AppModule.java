package com.hm.android.hmapp.dragger;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.api.AddCookieIntercepter;
import com.hm.android.hmapp.api.ApiService;
import com.hm.android.hmapp.api.ReceiveCookieInterceptor;
import com.hm.android.hmapp.bean.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 * <p>
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017.year. All rights reserved.
 * <p>
 * Created by jinxiangdong on 2017/11/8.
 */
@Module
@Singleton
public class AppModule {
    BaseApplication application;
    public AppModule(BaseApplication application){
        this.application = application;
    }

    @Provides
    public BaseApplication provideBaseApplication(){
        return  application;
    }

    @Provides
    public OkHttpClient provideOkHttpClient(AddCookieIntercepter addCookieIntercepter , ReceiveCookieInterceptor receiveCookieInterceptor ){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.READ_TIMEOUT , TimeUnit.SECONDS)
                .connectTimeout( Constants.CONNECT_TIMEOUT , TimeUnit.SECONDS )
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor( addCookieIntercepter )
                .addInterceptor( receiveCookieInterceptor )
                .build();

        return  okHttpClient;
    }

    @Provides
    public ReceiveCookieInterceptor provideReceiveCookieInterceptor(BaseApplication baseApplication){
        return new ReceiveCookieInterceptor(baseApplication);
    }

    @Provides
    public AddCookieIntercepter provideAddCookieInterceptor(BaseApplication baseApplication){
        return new AddCookieIntercepter(baseApplication);
    }

    @Provides
    public Gson provideGson(){
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    public GsonConverterFactory provideGsonConverter(Gson gson ){
        return  GsonConverterFactory.create( gson );
    }

    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapter(){
        return  RxJava2CallAdapterFactory.create();
    }

    @Provides
    public Retrofit provideRetroft(OkHttpClient okHttpClient , GsonConverterFactory gsonConverterFactory , RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder().baseUrl( Constants.BASE_URL )
                .client(okHttpClient)
                .addConverterFactory( gsonConverterFactory )
                .addCallAdapterFactory( rxJava2CallAdapterFactory)
                .build();
        return retrofit;
    }

    @Provides
    public ApiService provideApiService(Retrofit retrofit){
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }

//    @Provides
//    public HeaderIntercepter provideHeaderIntercepter(){
//        HeaderIntercepter headerIntercepter=new HeaderIntercepter();
//        return headerIntercepter;
//    }


//    @Provides
//    public Variable provideVariable(){
//        return new Variable();
//    }

}
