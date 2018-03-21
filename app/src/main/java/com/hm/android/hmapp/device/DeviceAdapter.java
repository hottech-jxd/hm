package com.hm.android.hmapp.device;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.infos1;
import com.hm.android.hmapp.bean.infos4;

import java.util.List;

/**
 * Created by jinxiangdong on 2018/1/16.
 */

public class DeviceAdapter extends BaseQuickAdapter<infos4, BaseViewHolder> {

    public DeviceAdapter(@Nullable List<infos4> data) {
        super(R.layout.layout_device_item ,    data);
    }

    @Override
    protected void convert(BaseViewHolder helper, infos4 item) {

        helper.setText( R.id.device_item_seq , String.valueOf( helper.getAdapterPosition()+1 ) );

        helper.setText(R.id.device_item_name, item.getParemeterName());

        String status = item.getDataValue();
        if(TextUtils.isEmpty(status))return;

        //helper.setBackgroundRes( R.id.device_item_status , status.equals("1") ? R.drawable.shape_red_circle : R.drawable.shape_green_circle );

        if( status.equals("1")){

            helper.setBackgroundRes(R.id.device_item_status , R.drawable.shape_trans_circle);
            helper.setBackgroundRes(R.id.device_item_name , R.drawable.shape_organe_circle);

        }else{
            helper.setBackgroundRes(R.id.device_item_status , R.drawable.shape_white_circle);
            helper.setBackgroundRes(R.id.device_item_name , R.drawable.shape_blue_circle);
        }

    }
}
