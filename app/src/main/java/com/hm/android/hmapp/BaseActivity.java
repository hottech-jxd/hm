package com.hm.android.hmapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.hm.android.hmapp.bean.ApiResult;
import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.login.LoginActivity;
import com.hm.android.hmapp.mvp.IPresenter;
import com.hm.android.hmapp.mvp.IView;
import com.hm.android.hmapp.utils.PreferenceHelper;
import com.hm.android.hmapp.utils.StatusBarUtil;
import com.hm.android.hmapp.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, LifecycleProvider<ActivityEvent> {
    protected Unbinder unbinder;
    BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    @Inject
    protected P iPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        //禁止横屏
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        setStatusBarTextBlackColor();
        setContentView();
        initView();
    }


    protected void setContentView(){}

    protected abstract void initView();

    /**
     *  此功能在 android 6.0以上系统适用
     *设置白底黑字的状态栏
     */
    private boolean setStatusBarTextBlackColor(){
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int newUiVisibility = this.getWindow().getDecorView().getSystemUiVisibility();
            newUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            this.getWindow().getDecorView().setSystemUiVisibility(newUiVisibility);
            StatusBarUtil.setColorNoTranslucent(this , ContextCompat.getColor(this , R.color.statusbarColor));
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);

        //MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);

        //MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        if(unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
        if(iPresenter!=null){
            iPresenter.onDestory();
            iPresenter=null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(String msg) {
        //Snackbar.make(this.getWindow().getDecorView(),msg,Snackbar.LENGTH_LONG).show();
        ToastUtils.showLongToast(msg);
    }

    @Override
    public void error(String msg) {
        toast(msg);
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    //@Nonnull
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    //@Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent( ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    //@Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    protected void startActivity(Class classz){
        Intent intent=new Intent(this , classz);
        startActivity(intent);
    }

    public void startActivity(Class classz , String key , Serializable serializable){
        Intent intent=new Intent(this , classz);
        intent.putExtra(key , serializable);
        startActivity(intent);
    }

    protected void startActivityForResult( int requestCode , Class classz){
        Intent intent=new Intent(this , classz);
        this.startActivityForResult(intent ,  requestCode);
    }
    protected void startActivityForResult( int requestCode , Intent intent ){
        this.startActivityForResult(intent ,  requestCode);
    }

    protected void skipActivity(Class classz){
        startActivity(classz);
        this.finish();
    }

    protected void skipActivity(Class classz , String key , Bundle bundle){
        Intent intent=new Intent(this , classz);
        intent.putExtra(key , bundle);
        startActivity(intent);
        this.finish();
    }

    protected void skipActivity(Class classz , String key , boolean booleanv ){
        Intent intent=new Intent(this , classz);
        intent.putExtra(key , booleanv );
        startActivity(intent);
        this.finish();
    }

    protected void skipActivityNeedLogin(Class classz){
        if(isLogin()) {
            skipActivity(classz);
            return;
        }
        skipActivity(LoginActivity.class);
    }

    protected void skipActivityNeedLogin(Class classz , String key , Bundle bundle ){
        if(isLogin()) {
            skipActivity(classz , key, bundle );
            return;
        }
        skipActivity(LoginActivity.class , key , bundle);
    }


    protected boolean isLogin(){
        if(BaseApplication.single.getUserBean()==null
                || TextUtils.isEmpty( BaseApplication.single.getUserBean().getPukId()) ) return false;
        return true;
    }

    protected void startActivityNeedLogin(Class classz){
        if(isLogin()) {
            startActivity(classz);
            return;
        }
        startActivity(LoginActivity.class);
    }

    protected void startActivityNeedLogin(Intent intent){
        if(isLogin()) {
            startActivity(intent);
            return;
        }
        startActivity(LoginActivity.class);
    }

    protected void startActivityNeedLogin(Class classz , String name , Serializable serializable){
        if(isLogin()) {
            startActivity(classz , name , serializable );
            return;
        }
        Intent intent = new Intent( this , classz);

        startActivity(intent);
    }

    /**
     * 处理服务端返回的公共错误代码
     * @param apiResult
     * @return
     */
//    protected boolean processCommonErrorCode(ApiResult apiResult){
//        if(//apiResult.getResultCode() == ApiResultCodeEnum.PARAMETER_ERROR.getCode() ||
//                //apiResult.getResultCode() == ApiResultCodeEnum.SIGN_NOT_PASS.getCode() ||
//                //apiResult.getResultCode() == ApiResultCodeEnum.SIGN_ERROR .getCode()  ||
//                //apiResult.getResultCode()==ApiResultCodeEnum.TOKEN_ERROR.getCode()
//                ){
//            //toast( apiResult.getResultMsg());
//            //toast(Constants.MESSAGE_TOKEN_LOST );
//
//            //EventBus.getDefault().post(new LogoutEvent());
//
//            skipActivity(LoginActivity.class);
//            return true;
//        }
//        return false;
//    }


}
