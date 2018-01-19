package com.hm.android.hmapp.main;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.DeviceResult;

import java.util.List;

/**
 * Created by jinxiangdong on 2018/1/16.
 */

public class MainAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {

    public MainAdapter(@Nullable List<DeviceBean> data) {
        super(R.layout.layout_main_item ,    data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceBean item) {
        helper.setText(R.id.main_item_name, item.getDeviceName());
    }
}
