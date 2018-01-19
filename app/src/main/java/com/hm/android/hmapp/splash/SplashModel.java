package com.hm.android.hmapp.splash;


import com.hm.android.hmapp.api.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

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
public class SplashModel implements ISplashModel {
    private ApiService apiService;
    private LifecycleProvider lifecycleProvider;

    public SplashModel(ApiService apiService , LifecycleProvider lifecycleProvider){
        this.apiService=apiService;
        this.lifecycleProvider=lifecycleProvider;
    }





//    @Override
//    public void init(Observer<MergeInitBean> observer) {
//        Observable initObservable = apiService.init();
//
//        Observable readCityObservable = getReadCityDataObservable();
//
//        Observable.zip(readCityObservable, initObservable, new BiFunction<ArrayList<Province>, ApiResult<InitBean> , MergeInitBean>() {
//            @Override
//            public MergeInitBean apply(ArrayList<Province>  cityData , ApiResult<InitBean> initBean ) throws Exception {
//                MergeInitBean mergeInitBean = new MergeInitBean();
//                mergeInitBean.setCityData( cityData );
//                mergeInitBean.setInitBean( initBean );
//                return mergeInitBean;
//            }
//        }).subscribeOn(Schedulers.newThread())
//          .observeOn(AndroidSchedulers.mainThread())
//         .compose( lifecycleProvider.<MergeInitBean>bindToLifecycle() )
//        .subscribe(observer);
//    }

    @Override
    public void onDestory() {
        apiService=null;
        lifecycleProvider=null;
    }
}
