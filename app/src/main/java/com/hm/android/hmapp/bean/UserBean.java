package com.hm.android.hmapp.bean;

/**
 * Created by jinxiangdong on 2018/1/15.
 */

public class UserBean extends BaseBean {
    /**
     * 用户id
     */
    private String pukId;
    /**
     * 用户名
     */
    private String userName;
    private String userPwd;
    /**
     * 企业类型
     */
    private String jurisdiction;


    public String getPukId() {
        return pukId;
    }

    public void setPukId(String pukId) {
        this.pukId = pukId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
