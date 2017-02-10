package com.itheima.redboyclient.domain;

/**
 * Created by fee1in on 2017/2/9.
 */
public class SearchTitle {
    private String title;
    private boolean isShow;


    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
