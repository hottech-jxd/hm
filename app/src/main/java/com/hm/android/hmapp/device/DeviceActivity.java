package com.hm.android.hmapp.device;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.ProgressWidget;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.main.DaggerMainComponent;
import com.hm.android.hmapp.main.IMainPresenter;
import com.hm.android.hmapp.main.IMainView;
import com.hm.android.hmapp.main.MainModule;
import com.hm.android.hmapp.utils.DensityUtils;
import com.hm.android.hmapp.widget.GridDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceActivity extends BaseActivity <IMainPresenter>
        implements BaseQuickAdapter.OnItemClickListener , IMainView{


    @BindView(R.id.header_left)
    ImageView header_left;
    @BindView(R.id.header_title)
    TextView header_title;
    @BindView(R.id.device_recyclerView)
    RecyclerView device_recyclerview;
    @BindView(R.id.device_toppicture)
    SimpleDraweeView device_toppicture;
    @BindView(R.id.device_progress)
    ProgressWidget device_progress;

    DeviceAdapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView() {
        super.setContentView();
        setContentView(R.layout.activity_device);
    }

    @Override
    protected void initView() {
        unbinder= ButterKnife.bind(this);

        header_left.setImageResource(R.drawable.style_left_arrow);

        header_title.setText("智能家具系统");

        int sw = DensityUtils.getScreenW(this);
        int h = sw * 1/2;
        ViewGroup.LayoutParams layoutParams = device_toppicture.getLayoutParams();
        layoutParams.height = h;
        layoutParams.width = sw;
        device_toppicture.setLayoutParams(layoutParams);


        List<DeviceBean> data = new ArrayList<>();
        for(int i =0;i<10;i++){
            DeviceBean bean =new DeviceBean();
            bean.setDeviceName("测试1");
            data.add(bean);
        }
        deviceAdapter = new DeviceAdapter(data);
        device_recyclerview.setLayoutManager(new GridLayoutManager(this,4));
        device_recyclerview.setAdapter(deviceAdapter);
        deviceAdapter.setOnItemClickListener(this);
        GridDivider.Builder builder = new GridDivider.Builder(this , 2);
        builder.setmDivider(new ColorDrawable(ContextCompat.getColor(this , R.color.space_color)));
        device_recyclerview.addItemDecoration( builder.build());

        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        DeviceBean bean =(DeviceBean) adapter.getItem(position);



        iPresenter.updateTrigger(bean.getPukId() , "1");

    }

    @Override
    public void mainCallback(List<DeviceResult> object) {

    }

    @Override
    public void updateTriggerCallback(Object object) {
        hideProgress();


    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);
        device_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        device_progress.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        device_progress.setVisibility(View.GONE);
        super.error(msg);
    }
}
