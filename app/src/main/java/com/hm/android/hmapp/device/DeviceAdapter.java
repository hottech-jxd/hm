package com.hm.android.hmapp.device;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.DeviceBean;

import java.util.List;

/**
 * Created by jinxiangdong on 2018/1/16.
 */

public class DeviceAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {

    public DeviceAdapter(@Nullable List<DeviceBean> data) {
        super(R.layout.layout_device_item ,    data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceBean item) {
        helper.setText(R.id.device_item_name, item.getDeviceName());
    }
}
