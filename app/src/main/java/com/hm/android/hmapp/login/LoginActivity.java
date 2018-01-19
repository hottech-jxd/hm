package com.hm.android.hmapp.login;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.main.MainActivity;
import com.hm.android.hmapp.ProgressWidget;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.utils.GsonUtil;
import com.hm.android.hmapp.utils.PreferenceHelper;

import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<ILoginPresenter> implements ILoginView {
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
//    @BindView(R.id.login_forgetpassword)
//    TextView loginForgetpassword;
    @BindView(R.id.login_login)
    Button loginLogin;
//    @BindView(R.id.login_register)
//    TextView loginRegister;
//    @BindView(R.id.login_register_lay)
//    LinearLayout loginRegisterLay;
    @BindView(R.id.login_progress)
    ProgressWidget loginProgress;
//    @BindView(R.id.login_error)
//    ErrorWidget loginError;
//    @BindView(R.id.login_lay_qrcode)
//    FrameLayout loginLayQrcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        //super.initView();
        unbinder = ButterKnife.bind(this);
        //EventBus.getDefault().register(this);

        PreferenceHelper.writeString(BaseApplication.single , Constants.PREF_FILENAME , Constants.PREF_USER , "");
        BaseApplication.single.setUserBean(null);

        DaggerLoginComponent.builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .injectLogin(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({ R.id.login_login })
    public void onViewClicked(View v) {
        if(v.getId() == R.id.login_login) {
           login();
        }
    }



    protected void login(){
        String phone= loginPhone.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        if( phone.isEmpty() ){
            loginPhone.setError("请输入用户名称");
            loginPhone.requestFocus();
            return;
        }

        if( password.isEmpty() ){
            loginPassword.setError("请输入密码");
            loginPassword.requestFocus();
            return;
        }


        //String enPassword = DigestUtils.md5(password).toUpperCase();
        String enPassword = password;

        iPresenter.login(phone,enPassword);

    }

    @Override
    public void showProgress(String msg) {
        super.showProgress(msg);
        //loginError.setVisibility(View.GONE);
        loginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        //loginError.setVisibility(View.GONE);
        loginProgress.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void error(String msg) {
        super.error(msg);
        loginProgress.setVisibility(View.GONE);
        //loginError.setVisibility(View.VISIBLE);
        //loginError.setError(msg);
        toast(msg);

        //skipActivity(MainActivity.class);
    }

    @Override
    public void loginCallback(UserBean userBean) {
        String json = GsonUtil.getGson().toJson(userBean);
        PreferenceHelper.writeString( this , Constants.PREF_FILENAME , Constants.PREF_USER ,  json);
        BaseApplication.single.setUserBean( userBean);

        //setJpushAlias( userBean );

        gotoActivity();
    }

//    /**
//     * 登录成功，设置极光推送的别名为 手机号
//     * @param userBean
//     */
//    private void setJpushAlias(UserBean userBean){
//        PushHelper.bindingUserId(  String.valueOf( userBean.getUserId()) , userBean.getUserName() , "","" ,"" );
//    }


    protected void gotoActivity(){

        skipActivity(MainActivity.class );
    }


    /**
     * 注册成功以后，关闭本界面
     * @param loginCloseEvent
     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventLoginClose(LoginCloseEvent loginCloseEvent){
//        this.finish();
//    }
}
