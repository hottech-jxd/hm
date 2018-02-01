package com.hm.android.hmapp.splash;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.login.LoginActivity;
import com.hm.android.hmapp.main.MainActivity;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.utils.GsonUtil;
import com.hm.android.hmapp.utils.PreferenceHelper;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<ISplashPresenter> implements ISplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);

        initVariable();

        DaggerSplashComponent.builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        //iPresenter.init();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gotoActivity();
//            }
//        },2000);

        gotoActivity();
    }


    protected void initVariable() {
        String json = PreferenceHelper.readString(this, Constants.PREF_FILENAME, Constants.PREF_USER, "");
        if (!json.isEmpty()) {
            BaseApplication.single.setUserBean(GsonUtil.getGson().fromJson(json, UserBean.class));
        }

//        HeaderParameter headerParameter = BaseApplication.single.getVariable().getHeaderParameter();
//        if (headerParameter == null) {
//            headerParameter = new HeaderParameter();
//            BaseApplication.single.getVariable().setHeaderParameter(headerParameter);
//        }
//
//        headerParameter.setAppVersion(BuildConfig.VERSION_NAME);
//        headerParameter.setMerchantId(Constants.MerchantId);
//        headerParameter.setMobileType(Build.MODEL);
//        headerParameter.setOsVersion(String.valueOf(Build.VERSION.SDK_INT));
//        headerParameter.setUserId(BaseApplication.single.getVariable().getUserBean() == null ? 0 : BaseApplication.single.getVariable().getUserBean().getUserId());
//        headerParameter.setUserToken(BaseApplication.single.getVariable().getUserBean() == null ? "" : BaseApplication.single.getVariable().getUserBean().getUserToken());
//        headerParameter.setUserToken(headerParameter.getUserToken() == null ? "" : headerParameter.getUserToken());
//        headerParameter.setOsType(Constants.OS_TYPE);
//        headerParameter.setHwid(headerParameter.getHwid()==null?"":headerParameter.getHwid());

    }

//    @Override
//    public void readCityDataCallback(ArrayList<Province> list) {
//        BaseApplication.single.getVariable().setCityData(list);
//        skipActivity(MainActivity.class);
//    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void toast(String msg) {
        //super.toast(msg);
    }

    @Override
    public void error(String msg) {
        //super.error(msg);
        gotoActivity();
    }


    protected void gotoActivity(){
        //获得推送信息
//        Bundle bundlePush= null;
//        if (null != getIntent() && getIntent().hasExtra(Constants.INTENT_PUSH_KEY)) {
//            bundlePush = getIntent().getBundleExtra(Constants.INTENT_PUSH_KEY);
//        }

        skipActivityNeedLogin(LoginActivity.class );
    }


}
