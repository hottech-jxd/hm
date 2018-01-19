package com.hm.android.hmapp.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface IView {
    void showProgress(String msg);

    void hideProgress();

    void toast(String msg);

    void error(String msg );

    LifecycleTransformer bindLifecycle();
}
