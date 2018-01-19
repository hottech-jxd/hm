package com.hm.android.hmapp.splash;




import com.hm.android.hmapp.dragger.AppComponent;

import dagger.Component;

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
@Component(modules = {SplashModule.class},dependencies = {AppComponent.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
    ISplashPresenter getSplashPresenter();
}
