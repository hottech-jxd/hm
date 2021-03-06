package com.hm.android.hmapp.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.BuildConfig;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.CloseEvent;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.login.LoginActivity;
import com.hm.android.hmapp.mvp.IView;
import com.hm.android.hmapp.utils.PreferenceHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity
        implements IView
{

    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.header_left)
    ImageView headerLeft;
    @BindView(R.id.setting_quit)
    TextView settingQuit;
//    @BindView(R.id.setting_avatar)
//    SimpleDraweeView setting_avatar;
    @BindView(R.id.setting_customer)
    TextView setting_customer;
    @BindView(R.id.setting_username)
    TextView setting_username;
    @BindView(R.id.setting_version)
    TextView setting_version;
    @BindView(R.id.setting_devicetype)
    TextView setting_devicetype;


    private String headImgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        super.setContentView();
        setContentView(R.layout.activity_setting);
    }


    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);

        headerLeft.setImageResource(R.drawable.style_left_arrow);
        headerTitle.setText("设置");
        headerTitle.setTextColor(ContextCompat.getColor(this , R.color.white));


        setting_customer.setText( BaseApplication.single.getUserBean().getJurisdiction() );
        setting_username.setText(BaseApplication.single.getUserBean().getUserName());
        setting_version.setText(BuildConfig.VERSION_NAME);

        if(getIntent()!=null && getIntent().hasExtra(Constants.INTENT_EnterpriseName)){
            setting_customer.setText( getIntent().getStringExtra(Constants.INTENT_EnterpriseName) );
        }
        if(getIntent()!=null && getIntent().hasExtra(Constants.INTENT_DEVICETYPE)){
            setting_devicetype.setText(getIntent().getStringExtra(Constants.INTENT_DEVICETYPE));
        }

//        DaggerMyComponent
//                .builder()
//                .appComponent(BaseApplication.single.getAppComponent())
//                .myModule(new MyModule(this))
//                .build()
//                .inject2(this);

    }

    @OnClick({R.id.setting_quit,R.id.header_left , R.id.setting_lay_avatar,
            R.id.setting_about, R.id.setting_question,
            R.id.setting_protocal
    })
    public void onViewClicked(View v){
        if(v.getId()==R.id.setting_quit){
            EventBus.getDefault().post(new CloseEvent() );
            Boolean autoLogin = false;
            skipActivity(LoginActivity.class , Constants.INTENT_AUTOLOGIN , autoLogin );
        }else if(v.getId()==R.id.header_left){
            this.finish();
        }
    }


}
