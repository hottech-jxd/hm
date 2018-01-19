package com.hm.android.hmapp.splash;



import com.hm.android.hmapp.api.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 * <p>
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017.year. All rights reserved.
 * <p>
 * Created by jinxiangdong on 2017/11/10.
 */
@Module
public class SplashModule {

    private SplashActivity splashActivity;
    public SplashModule(SplashActivity splashActivity){
        this.splashActivity = splashActivity;
    }

    @Provides
    public ISplashModel provideSplashModel(ApiService apiService){
        return new SplashModel(apiService , splashActivity );
    }

    @Provides
    public ISplashView provideSplashView(){
        return splashActivity;
    }

    @Provides
    public ISplashPresenter provideSplashPresenter(ISplashView iSplashView ,ISplashModel iSplashModel){
        return new SplashPresenter(iSplashView ,iSplashModel);
    }

}
