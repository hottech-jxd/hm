package com.hm.android.hmapp;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hm.android.hmapp.bean.UserBean;
import com.hm.android.hmapp.dragger.AppComponent;
import com.hm.android.hmapp.dragger.AppModule;
import com.hm.android.hmapp.dragger.DaggerAppComponent;
import com.hm.android.hmapp.utils.CrashHandler;


/**
 * Created by Administrator on 2017/11/6.
 */

public class BaseApplication extends Application {
    public static BaseApplication single;
    //@Inject
    //Variable variable;

    /**
     * 用户信息
     */
    private UserBean userBean;

    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        single=this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);


        CrashHandler.getInstance().init(this);

        Fresco.initialize(this);
        //ThirdPartyInit.init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //MobclickAgent.onKillProcess(this);

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

//    public Variable getVariable() {
//        return variable;
//    }

//    public void setVariable(Variable variable) {
//        this.variable = variable;
//    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    //    public long getUserId(){
//        return this.variable==null || this.variable.getUserBean()==null ? 0 : this.variable.getUserBean().getUserId();
//    }
//    public String getUserToken(){
//        return this.variable==null || this.variable.getUserBean()==null? "":this.variable.getUserBean().getUserToken();
//    }
}
