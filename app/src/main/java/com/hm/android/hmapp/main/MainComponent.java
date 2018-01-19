package com.hm.android.hmapp.main;

import com.hm.android.hmapp.device.DeviceActivity;
import com.hm.android.hmapp.dragger.AppComponent;
import com.hm.android.hmapp.login.ILoginPresenter;
import com.hm.android.hmapp.login.LoginActivity;
import com.hm.android.hmapp.login.LoginModule;

import dagger.Component;

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
@Component(modules = {MainModule.class} , dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);

    void inject(DeviceActivity deviceActivity);

    IMainPresenter getMainPresenter();



}
