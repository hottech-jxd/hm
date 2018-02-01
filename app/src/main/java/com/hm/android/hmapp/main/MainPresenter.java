package com.hm.android.hmapp.main;


import android.test.mock.MockApplication;
import android.text.TextUtils;

import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.bean.infoAll;
import com.hm.android.hmapp.login.ILoginModel;
import com.hm.android.hmapp.login.ILoginPresenter;
import com.hm.android.hmapp.login.ILoginView;

import java.util.List;
import java.util.Map;

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
public class MainPresenter implements IMainPresenter {
    private IMainView iMainView;
    private IMainModel iMainModel;

    public MainPresenter(IMainView iMainView, IMainModel iMainModel){
        this.iMainView = iMainView;
        this.iMainModel = iMainModel;
    }

    @Override
    public void main(String userId) {
        iMainModel.main(userId , new Observer<List<DeviceResult>>() {
            @Override
            public void onSubscribe(Disposable d) {
                iMainView.showProgress(Constants.TIP_LOADING);
            }

            @Override
            public void onNext(List<DeviceResult> objectApiResult) {
                iMainView.hideProgress();
//                if(objectApiResult.getResultCode() != ApiResultCodeEnum.SUCCESS.getCode()){
//                    iLoginView.error( objectApiResult.getResultMsg());
//                }else{
//                    iLoginView.loginCallback( objectApiResult.getData() );
//                }

                if(objectApiResult ==null ){
                    iMainView.error( "请求错误" );
                }else{
                    iMainView.mainCallback(objectApiResult);
                }

            }

            @Override
            public void onError(Throwable e) {
                iMainView.hideProgress();
                iMainView.error(e.getMessage());
            }

            @Override
            public void onComplete() {
                iMainView.hideProgress();
            }
        });
    }


    @Override
    public void updateTrigger(String pubId , String datavale ) {
        iMainModel.updateTrigger(pubId , datavale , new Observer<Map>() {
            @Override
            public void onSubscribe(Disposable d) {
                iMainView.showProgress(Constants.TIP_LOADING);
            }

            @Override
            public void onNext(Map objectApiResult) {
                iMainView.hideProgress();

                if(objectApiResult ==null ){
                    iMainView.error( "请求错误" );
                }else{
                    iMainView.updateTriggerCallback(objectApiResult);
                }

            }

            @Override
            public void onError(Throwable e) {
                iMainView.hideProgress();
                iMainView.error(e.getMessage());
            }

            @Override
            public void onComplete() {
                iMainView.hideProgress();
            }
        });
    }

    @Override
    public void getRealJK(String deviceId) {
        iMainModel.getRealJK(deviceId , new Observer<List<infoAll>>() {
            @Override
            public void onSubscribe(Disposable d) {
                iMainView.showProgress(Constants.TIP_LOADING);
            }

            @Override
            public void onNext(List<infoAll> objectApiResult) {
                iMainView.hideProgress();
//                if(objectApiResult.getResultCode() != ApiResultCodeEnum.SUCCESS.getCode()){
//                    iLoginView.error( objectApiResult.getResultMsg());
//                }else{
//                    iLoginView.loginCallback( objectApiResult.getData() );
//                }

                if(objectApiResult ==null ){
                    iMainView.error( "请求错误" );
                }else{
                    iMainView.getRealJKCallback(objectApiResult);
                }

            }

            @Override
            public void onError(Throwable e) {
                iMainView.hideProgress();
                iMainView.error(e.getMessage());
            }

            @Override
            public void onComplete() {
                iMainView.hideProgress();
            }
        });
    }

    @Override
    public void onDestory() {
        if(iMainModel !=null){
            iMainModel.onDestory();
            iMainModel=null;
        }
        iMainView=null;
    }
}
