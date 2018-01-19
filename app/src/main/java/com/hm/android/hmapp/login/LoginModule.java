package com.hm.android.hmapp.login;


import com.hm.android.hmapp.api.ApiService;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.Nullable;

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
public class LoginModule {
    private LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity){
        this.loginActivity=loginActivity;
    }


    @Provides
    public ILoginPresenter provideLoginPresenter(ILoginView iLoginView ,   ILoginModel iLoginModel ){
        return new LoginPresenter( iLoginView ,  iLoginModel );
    }

    @Provides
    public ILoginModel provideLoginModel(ApiService apiService){
        return new LoginModel( apiService , loginActivity ,null );
    }

    @Provides
    public ILoginView provideLoginView(){
        return this.loginActivity;
    }


}
