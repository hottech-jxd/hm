package com.hm.android.hmapp.main;


import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.bean.infoAll;
import com.hm.android.hmapp.mvp.IView;

import java.util.List;
import java.util.Map;

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
public interface IMainView extends IView {
        void mainCallback(List<DeviceResult> object);

        void updateTriggerCallback(Map object);

        void getRealJKCallback(List<infoAll> infoAll);
}
