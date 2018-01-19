package com.hm.android.hmapp.api;


import com.hm.android.hmapp.bean.ApiResult;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.UserBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

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
public interface ApiService {



    /**
     * 登录接口
     *
     * @param userName  用户名
     * @param userPwd     密码(md5)或验证码
     * @return
     */
    @FormUrlEncoded
    @POST("login/appLogin")
    Observable<UserBean> login(@Field("userName") String userName,
                                          @Field("userPwd") String userPwd );

    /***
     *
     * @return
     */
    @FormUrlEncoded
    @POST("DeviceInfoDBO/findDeviceList")
    Observable<List<DeviceResult>> getDeviceList(@Field("userId") String userId);

    /**
     *
     * @param pukId
     * @return
     */
    @FormUrlEncoded
    @POST("TiggerInfoDBO/updateTigger")
    Observable<Object> updateTigger(@Field("pukId") String pukId , @Field("dataValue") String dataValue);

//
//    /**
//     * 用户修改（忘记）密码接口
//     *
//     * @param username
//     * @param newPassword
//     * @param verifyCode
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("user/updatePassword")
//    Observable<ApiResult<Object>> updatePassword(@Field("username") String username, @Field("newPassword") String newPassword, @Field("verifyCode") String verifyCode);


//    /**
//     * 注册接口
//     *
//     * @param username
//     * @param password
//     * @param verifyCode
//     * @param userType 用户类型，1：借款人 0出借人 (12.18新增)
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("user/register")
//    Observable<ApiResult<UserBean>> register(@Field("username") String username, @Field("password") String password, @Field("verifyCode") String verifyCode , @Field("userType") int userType);
//
//
}
