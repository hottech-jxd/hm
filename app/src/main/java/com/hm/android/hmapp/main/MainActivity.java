package com.hm.android.hmapp.main;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.CloseEvent;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.bean.infoAll;
import com.hm.android.hmapp.device.DeviceActivity;
import com.hm.android.hmapp.setting.SettingActivity;
import com.hm.android.hmapp.utils.DensityUtils;
import com.hm.android.hmapp.utils.PreferenceHelper;
import com.hm.android.hmapp.utils.ToastUtils;
import com.hm.android.hmapp.widget.GridDivider;
import com.hm.android.hmapp.widget.RecycleItemDivider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView , BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.header_left)
    ImageView header_left;
    @BindView(R.id.header_title)
    TextView header_title;
    @BindView(R.id.header_right_image)
    ImageView header_right_image;
    @BindView(R.id.main_recyclerView)
    RecyclerView main_recyclerview;
//    @BindView(R.id.main_toppicture)
//    SimpleDraweeView main_toppicture;
    @BindView(R.id.tv_anfang)
    TextView tv_anfang;
    @BindView(R.id.tv_dianqi)
    TextView tv_dianqi;
    @BindView(R.id.tv_zhaoming)
    TextView tv_zhaoming;
    @BindView(R.id.tv_mengjin)
    TextView tv_mengjin;

    List<DeviceBean> manus;
    MainAdapter mainAdapter;
    long exitTime = 0;
    View emptyView;

    boolean isOpen_zhaoming=false;
    boolean isOpen_anfang = false;
    boolean isOpen_dianqi=false;
    boolean isOpen_mengjin=false;
    DeviceBean zhaoming;
    DeviceBean anfang;
    DeviceBean dianqi;
    DeviceBean mengjin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder= ButterKnife.bind(this);
        EventBus.getDefault().register(this);


        //header_left.setImageResource(R.drawable.style_left_arrow);
        header_right_image.setImageResource(R.mipmap.person);
        header_title.setText("智能家具系统");
        header_title.setTextColor(ContextCompat.getColor(this , R.color.white));

//        int sw = DensityUtils.getScreenW(this);
//        int h = sw * 1/2;
//        ViewGroup.LayoutParams layoutParams = main_toppicture.getLayoutParams();
//        layoutParams.height = h;
//        layoutParams.width = sw;
//        main_toppicture.setLayoutParams(layoutParams);
//
//        Uri uri = Uri.parse("res://"+getPackageName()+"/"+R.mipmap.none);
//
//        main_toppicture.setImageURI(uri);


        emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty , (ViewGroup) main_recyclerview.getParent() , false);
        ImageView emptyImage = emptyView.findViewById(R.id.empty_image);
        emptyImage.setImageResource(R.mipmap.empty);
        TextView emptyText = emptyView.findViewById(R.id.empty_text);
        emptyText.setText("暂无数据");



        manus=new ArrayList<>();
//        DeviceBean bean = new DeviceBean();
//        bean.setDeviceName("照明");
//        manus.add(bean);
//        bean = new DeviceBean();
//        bean.setDeviceName("安防");
//        manus.add(bean);
//        bean = new DeviceBean();
//        bean.setDeviceName("电器");
//        manus.add(bean);
//        bean = new DeviceBean();
//        bean.setDeviceName("门禁");
//        manus.add(bean);

        mainAdapter=new MainAdapter(manus);

        mainAdapter.setEmptyView(emptyView);


        main_recyclerview.setLayoutManager(new GridLayoutManager(this ,2));
        main_recyclerview.setAdapter(mainAdapter);


        GridDivider.Builder builder = new GridDivider.Builder(this , 2);
        builder.setmDivider(new ColorDrawable(ContextCompat.getColor(this , R.color.space_color)));
        int h = DensityUtils.dip2px(this , 10);
        builder.setV_spacing(h);
        builder.setH_spacing(h);

        main_recyclerview.addItemDecoration( builder.build());
        mainAdapter.setOnItemClickListener(this);


        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        iPresenter.main(BaseApplication.single.getUserBean().getPukId() );

    }

    @OnClick({R.id.header_right_image ,
            R.id.tv_mengjin , R.id.tv_zhaoming, R.id.tv_dianqi , R.id.tv_anfang})
    public void onViewClicked(View v){
        if(v.getId()==R.id.header_right_image){
            Intent intent=new Intent(this, SettingActivity.class);
            intent.putExtra(Constants.INTENT_EnterpriseName , manus==null? "": manus.get(0).getEnterpriseName());
            intent.putExtra(Constants.INTENT_DEVICETYPE , manus==null?"":manus.get(0).getProductType());
            startActivity(intent);
        }else if(v.getId()==R.id.tv_anfang){
            gotoAction(isOpen_anfang , anfang);
        }else if(v.getId()==R.id.tv_dianqi){
            gotoAction(isOpen_dianqi , dianqi);
        }else if(v.getId() ==R.id.tv_zhaoming){
            gotoAction(isOpen_zhaoming , zhaoming);
        }else if(v.getId()==R.id.tv_mengjin){
            gotoAction(isOpen_mengjin , mengjin);
        }
    }

    private void gotoAction(Boolean isOpen , DeviceBean bean){
        if(!isOpen){
            toast("该功能暂未开放");
            return;
        }
        Intent intent=new Intent(this , DeviceActivity.class);
        intent.putExtra(Constants.INTENT_DEVICE_ID , bean.getPukId());
        startActivity(intent);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        DeviceBean bean = (DeviceBean)adapter.getItem(position);
        if(!bean.isEnabled()){
            toast("该功能暂未开放");
            return;
        }

        Intent intent=new Intent(this , DeviceActivity.class);
        intent.putExtra(Constants.INTENT_DEVICE_ID , bean.getPukId());
        startActivity(intent);
    }

    @Override
    protected void initView() {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 2秒以内按两次推出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showLongToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
                PreferenceHelper.writeString(BaseApplication.single, Constants.PREF_FILENAME , Constants.PREF_USER , "");
                PreferenceHelper.writeStringSet(BaseApplication.single , Constants.PREF_FILENAME , Constants.PREF_COOKIE , null);
                //EventBus.getDefault().post(new ExistEvent());
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void mainCallback(List<DeviceResult> object) {
        //ToastUtils.showLongToast("ssss");

        if(object==null || object.size()<1 || object.get(0).getLoadDevices()==null || object.get(0).getLoadDevices().size()<1){
            return;
        }

        manus  = object.get(0).getLoadDevices();
        for(int i=0;i<manus.size();i++){
            manus.get(i).setEnabled(true);
        }

        DeviceBean  bean = new DeviceBean();
        bean.setDeviceName("安防");
        bean.setEnabled(false);
        manus.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("电器");
        bean.setEnabled(false);
        manus.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("门禁");
        bean.setEnabled(false);
        manus.add(bean);


        mainAdapter.setNewData( object.get(0).getLoadDevices() );


//        DeviceBean deviceBean = object.get(0).getLoadDevices().get(0);
//        String deviceName = object.get(0).getLoadDevices().get(0).getDeviceName();
//
//        if(deviceName.equals("照明")){
//            isOpen_zhaoming=true;
//            zhaoming = deviceBean;
//        }else {
//            isOpen_zhaoming=false;
//        }
//        if(deviceName.equals("安防")){
//            isOpen_anfang=true;
//            anfang = deviceBean;
//        }else{
//            isOpen_anfang=false;
//        }
//        if(deviceName.equals("电器")){
//            isOpen_dianqi=true;
//            dianqi=deviceBean;
//        }else {
//            isOpen_dianqi=false;
//        }
//        if(deviceName.equals("门禁")){
//            isOpen_mengjin=true;
//            mengjin=deviceBean;
//        }else {
//            isOpen_mengjin=false;
//        }
    }

    @Override
    public void updateTriggerCallback(Map object) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventClose(CloseEvent event){
        this.finish();
    }

    @Override
    public void getRealJKCallback(List<infoAll> object) {

    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);
        mainAdapter.isUseEmpty(false);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        mainAdapter.isUseEmpty(false);
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
}
