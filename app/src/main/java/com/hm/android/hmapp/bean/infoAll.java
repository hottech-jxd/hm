package com.hm.android.hmapp.bean;

import java.util.List;

/**
 * Created by jinxiangdong on 2018/1/30.
 */

public class infoAll {
    private List<infos1> infos1;
    private List<Object> infos2;
    private List<Object> infos3;
    private List<infos4> infos4;


    public List<com.hm.android.hmapp.bean.infos1> getInfos1() {
        return infos1;
    }

    public void setInfos1(List<infos1> infos1) {
        this.infos1 = infos1;
    }

    public void setInfos4(List<infos4> infos4) {
        this.infos4 = infos4;
    }

    public Object getInfos2() {
        return infos2;
    }

    public void setInfos2(List<Object> infos2) {
        this.infos2 = infos2;
    }

    public List<Object> getInfos3() {
        return infos3;
    }

    public void setInfos3(List<Object> infos3) {
        this.infos3 = infos3;
    }

    public List<infos4> getInfos4() {
        return infos4;
    }
}
