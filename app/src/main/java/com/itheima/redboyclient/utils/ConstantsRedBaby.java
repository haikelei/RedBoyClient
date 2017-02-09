package com.itheima.redboyclient.utils;

public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.56.1:8080/RedBabyServer";
    public static final String URL_TOPIC = URL_SERVER + "/topic";
    public static final String URL_REGIST =URL_SERVER + "/register";
    public static final String URL_HOME = URL_SERVER + "/home";
    public static final String URL_SEARCH = URL_SERVER +"/search";
    public static final String URL_LOGIN = URL_SERVER +"/login";
    public static final String URL_SEARCH_RECOMMEND = URL_SEARCH +"/recommend";
    public static final String URL_BRAND = URL_SERVER + "/brand";


    public static final String NOHISTORY = "没有搜索记录";

    /**
     * topic请求码
     */
    public static final int REQUEST_CODE_HOME = 0;
    public static final int REQUEST_CODE_RECOMMEND = 1;
    //TODO 主页五个fragment使用0-4；

    public static final int REQUEST_CODE_TOPIC = 5;
    public static final int REQUEST_CODE_LOGIN = 6;
    public static final int REQUEST_CODE_REGIST = 7;
    public static final int REQUEST_CODE_BRAND = 8;
    public static final int REQUEST_CODE_SEARCH = 9;


}
