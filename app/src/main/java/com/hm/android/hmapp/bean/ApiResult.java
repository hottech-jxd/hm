package com.hm.android.hmapp.bean;

/**
 * Created by Administrator on 2017/10/26.
 */
public class ApiResult<T> {
    private String msg;
    private String result;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
