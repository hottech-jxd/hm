package com.hm.android.hmapp.device;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.infoAll;
import com.hm.android.hmapp.bean.infos1;
import com.hm.android.hmapp.bean.infos4;
import com.hm.android.hmapp.main.DaggerMainComponent;
import com.hm.android.hmapp.main.IMainPresenter;
import com.hm.android.hmapp.main.IMainView;
import com.hm.android.hmapp.main.MainModule;
import com.hm.android.hmapp.utils.DensityUtils;
import com.hm.android.hmapp.widget.GridDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String deviceId;
    View emptyView;

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


        emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty , (ViewGroup) device_recyclerview.getParent() , false);
        ImageView emptyImage = emptyView.findViewById(R.id.empty_image);
        emptyImage.setImageResource(R.mipmap.empty);
        TextView emptyText = emptyView.findViewById(R.id.empty_text);
        emptyText.setText("暂无数据");


        List<infos4> data = new ArrayList<>();

        if(getIntent()!=null && getIntent().hasExtra(Constants.INTENT_DEVICE_ID)){
            deviceId = getIntent().getStringExtra(Constants.INTENT_DEVICE_ID);
        }

//        for(int i =0;i<10;i++){
//            DeviceBean bean =new DeviceBean();
//            bean.setDeviceName("测试1");
//            data.add(bean);
//        }

        deviceAdapter = new DeviceAdapter(data);
        device_recyclerview.setLayoutManager(new GridLayoutManager(this,4));
        device_recyclerview.setAdapter(deviceAdapter);
        deviceAdapter.setOnItemClickListener(this);
        deviceAdapter.isUseEmpty(false);
        deviceAdapter.setEmptyView(emptyView);
        GridDivider.Builder builder = new GridDivider.Builder(this , 4);
        builder.setmDivider(new ColorDrawable(ContextCompat.getColor(this , R.color.space_color)));
        device_recyclerview.addItemDecoration( builder.build());

        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        if(!TextUtils.isEmpty(deviceId)) {
            iPresenter.getRealJK(deviceId);
        }
    }

    @OnClick({R.id.header_left})
    public void onViewClicked(View view){
        if(view.getId()==R.id.header_left){
            this.finish();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        infos4 bean =(infos4) adapter.getItem(position);


        String status = bean.getDataValue();
        if(TextUtils.isEmpty(status)){
            status="0";
        }

        status = status.equals("0")?"1":"0";

        String pid = bean.getPukId();

        iPresenter.updateTrigger(pid , status );

    }

    @Override
    public void mainCallback(List<DeviceResult> object) {

    }

    @Override
    public void updateTriggerCallback(Map object) {
        hideProgress();


        if(object==null){
            error("请求发生错误");
            return;
        }
        if(object.containsKey("result")){
            String result = object.get("result").toString();
            if(result.equals("success")){
                String msg = object.get("msg").toString();
                toast(msg);
                iPresenter.getRealJK(deviceId);
                return;
            }
        }


        error("请求失败");


    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);
        deviceAdapter.isUseEmpty(false);
        device_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        deviceAdapter.isUseEmpty(true);
        device_progress.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        hideProgress();
        super.error(msg);
    }

    @Override
    public void getRealJKCallback(List<infoAll> object) {
        deviceAdapter.isUseEmpty(true);

        if(object==null){
            return;
        }
        if(object.size()<1){
            return;
        }
        if(object.get(0).getInfos1()==null){
            return;
        }
        if(object.get(0).getInfos1().size()<1){
            return;
        }


//        for(int i = 0;i< object.get(0).getInfos4().size();i++){
//            infos4 infos4 = object.get(0).getInfos4().get(i);
//            for(int k=0;k<object.get(0).getInfos1().size();k++) {
//                infos1 infos1 = object.get(0).getInfos1().get(k);
//                if(infos4.getRegisterAddress().equals( infos1. ))
//                infos1.setInfos4(object.get(0).getInfos4().get(i));
//            }
//        }


        deviceAdapter.setNewData( object.get(0).getInfos4() );


    }
}
