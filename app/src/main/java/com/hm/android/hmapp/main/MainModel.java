package com.hm.android.hmapp.main;

import com.hm.android.hmapp.api.ApiService;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.login.ILoginModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;

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
public class MainModel implements IMainModel {
    ApiService apiService;
    LifecycleProvider lifecycleProvider;


    public MainModel(ApiService apiService , LifecycleProvider lifecycleProvider){
        this.apiService=apiService;
        this.lifecycleProvider =lifecycleProvider;
    }

    @Override
    public void main(String userId , Observer<List<DeviceResult>> observer) {
        Observable<List<DeviceResult>> apiResultObservable = apiService.getDeviceList( userId );

        apiResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<List<DeviceResult>>bindToLifecycle() )
                .subscribe(observer);

    }

    @Override
    public void updateTrigger(String pubId , String dataValue , Observer<Object> observer) {
        Observable<Object> apiResultObservable = apiService.updateTigger( pubId, dataValue );

        apiResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose( lifecycleProvider.<Object>bindToLifecycle() )
                .subscribe(observer);

    }


    @Override
    public void onDestory() {
        apiService=null;
        //lifecycleProvider=null;
    }
}
