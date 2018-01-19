package com.hm.android.hmapp.main;


import android.widget.ImageView;

import com.hm.android.hmapp.api.ApiService;
import com.hm.android.hmapp.login.ILoginModel;
import com.hm.android.hmapp.login.ILoginPresenter;
import com.hm.android.hmapp.login.ILoginView;
import com.hm.android.hmapp.login.LoginActivity;
import com.hm.android.hmapp.login.LoginModel;
import com.hm.android.hmapp.login.LoginPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

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
 * Created by jinxiangdong on 2017/11/8.
 */
@Module
public class MainModule {
    private IMainView iMainView;

    public MainModule(IMainView iMainView){
        this.iMainView=iMainView;
    }


    @Provides
    public IMainPresenter provideMainPresenter(IMainView iMainView ,   IMainModel iMainModel ){
        return new MainPresenter( iMainView ,  iMainModel );
    }

    @Provides
    public IMainModel provideMainModel(ApiService apiService){
        return new MainModel( apiService , (LifecycleProvider) iMainView  );
    }

    @Provides
    public IMainView provideMainView(){
        return this.iMainView;
    }


}
