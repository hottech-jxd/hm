package com.hm.android.hmapp.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hm.android.hmapp.bean.Constants;
import com.hm.android.hmapp.login.LoginActivity;

public class PushHandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pushProcess();
    }

    protected void pushProcess(){
//        if(null == getIntent() || !getIntent().hasExtra( Constants.INTENT_PUSH_KEY)) {
//            finish();
//            return;
//        }
//
//        Bundle bundle = getIntent().getBundleExtra(Constants.INTENT_PUSH_KEY );
//        if(null==bundle) {
//            finish();
//            return;
//        }


        //通过配置文件获取登录界面的类名，进行相应的登录操作
//        String loginActivityClassName = this.getString(R.string.login_activity_classname);
//        if( TextUtils.isEmpty(loginActivityClassName)) {
//            loginActivityClassName = PhoneLoginActivity.class.getName();
//        }

        String loginActivityClassName = LoginActivity.class.getName();


//        boolean loginActivityIsLoaded = AppUtil.isAppLoaded(this , loginActivityClassName);
//
//        if(loginActivityIsLoaded ){
//            Intent intent = new Intent();
//            intent.setClassName( this.getPackageName() , loginActivityClassName );
//            intent.putExtra( Constants.INTENT_PUSH_KEY , bundle);
//            this.startActivity(intent);
//            this.finish();
//            return;
//        }
//
//        boolean fragMainActivityIsLoaded = AppUtil.isAppLoaded(this , MainActivity.class.getName());
//        if( fragMainActivityIsLoaded){
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra( Constants.INTENT_PUSH_KEY , bundle);
//            this.startActivity(intent);
//            this.finish();
//            return;
//        }

//        Intent intent = new Intent(this, SplashActivity.class);
//        intent.putExtra( Constants.INTENT_PUSH_KEY , bundle);
//        this.startActivity(intent);
//        this.finish();
//        return;
    }
}
