package com.hm.android.hmapp.login;


import android.text.TextUtils;

import com.hm.android.hmapp.bean.ApiResult;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.UserBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public class LoginPresenter implements ILoginPresenter {
    private ILoginView iLoginView;
    private ILoginModel iLoginModel;

    public LoginPresenter(ILoginView iLoginView,  ILoginModel iLoginModel){
        this.iLoginModel = iLoginModel;
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String username, String password) {
        iLoginModel.login(username, password, new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                iLoginView.showProgress(Constants.TIP_LOADING);
            }

            @Override
            public void onNext(UserBean objectApiResult) {
                iLoginView.hideProgress();
//                if(objectApiResult.getResultCode() != ApiResultCodeEnum.SUCCESS.getCode()){
//                    iLoginView.error( objectApiResult.getResultMsg());
//                }else{
//                    iLoginView.loginCallback( objectApiResult.getData() );
//                }

                if(objectApiResult ==null || !TextUtils.isEmpty(objectApiResult.getResult())){
                    iLoginView.error( "登录失败" );
                }else{
                    iLoginView.loginCallback(objectApiResult);
                }

            }

            @Override
            public void onError(Throwable e) {
                iLoginView.hideProgress();
                iLoginView.error(e.getMessage());
            }

            @Override
            public void onComplete() {
                iLoginView.hideProgress();
            }
        });
    }


    @Override
    public void onDestory() {
        if(iLoginModel!=null){
            iLoginModel.onDestory();
            iLoginModel=null;
        }
        iLoginView=null;
    }
}
