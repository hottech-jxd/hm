package com.hm.android.hmapp.login;


import com.hm.android.hmapp.bean.ApiResult;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.mvp.IModel;

import io.reactivex.Observer;

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
public interface ILoginModel extends IModel {

    void login(String username , String password , Observer<UserBean> observer);

//    void register(String username , String password , String verifyCode , int userType , Observer<ApiResult<UserBean>> objectObserver);
//
//    void getVerifyCode(String phone , Observer<ApiResult<Object>> observer);
//
//    void updatePassword(String username , String newPassword  , String verifyCode , Observer<ApiResult<Object>>  observer );

}
