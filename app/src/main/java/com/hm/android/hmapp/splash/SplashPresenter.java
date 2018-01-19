package com.hm.android.hmapp.splash;


import io.reactivex.disposables.Disposable;

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
public class SplashPresenter implements ISplashPresenter {
    private ISplashView iSplashView;
    private ISplashModel iSplashModel;

    public SplashPresenter(ISplashView iSplashView , ISplashModel iSplashModel){
        this.iSplashView= iSplashView;
        this.iSplashModel=iSplashModel;
    }

//    @Override
//    public void readCityData() {
//
//        iSplashModel.readCityData(new Observer<ArrayList<Province>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                iSplashView.showProgress(Constants.TIP_LOADING);
//            }
//
//            @Override
//            public void onNext(ArrayList<Province> addressBeans) {
//                long id= Thread.currentThread().getId();
//                iSplashView.readCityDataCallback(addressBeans);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                iSplashView.hideProgress();
//                iSplashView.error(Constants.MESSAGE_ERROR);
//            }
//
//            @Override
//            public void onComplete() {
//                iSplashView.hideProgress();
//            }
//        });
//    }

//    @Override
//    public void init() {
//        iSplashModel.init(new Observer<MergeInitBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                iSplashView.showProgress(Constants.TIP_LOADING);
//            }
//
//            @Override
//            public void onNext(MergeInitBean objectApiResult) {
//                iSplashView.initCallback( objectApiResult );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                iSplashView.hideProgress();
//                iSplashView.error(Constants.MESSAGE_ERROR);
//            }
//
//            @Override
//            public void onComplete() {
//                iSplashView.hideProgress();
//            }
//        });
//    }

    @Override
    public void onDestory() {
        if(iSplashModel!=null){
            iSplashModel.onDestory();
            iSplashModel=null;
        }
        iSplashView=null;
    }
}
