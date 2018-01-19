package com.hm.android.hmapp.login;

import com.hm.android.hmapp.api.ApiService;
import com.hm.android.hmapp.bean.ApiResult;
import com.hm.android.hmapp.bean.UserBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;

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
public class LoginModel implements ILoginModel {
    ApiService apiService;
    LifecycleProvider lifecycleProviderLogin;
    LifecycleProvider lifecycleProviderRegister;

    public LoginModel(ApiService apiService , LifecycleProvider lifecycleProviderLogin , LifecycleProvider lifecycleProviderRegister){
        this.apiService=apiService;
        this.lifecycleProviderLogin =lifecycleProviderLogin;
        this.lifecycleProviderRegister = lifecycleProviderRegister;
    }

    @Override
    public void login(String username, String password, Observer<UserBean> observer) {
        Observable<UserBean> apiResultObservable = apiService.login(username,password );

        apiResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProviderLogin.<UserBean>bindToLifecycle() )
                .subscribe(observer);

    }


    @Override
    public void onDestory() {
        apiService=null;
        //lifecycleProvider=null;
    }
}
