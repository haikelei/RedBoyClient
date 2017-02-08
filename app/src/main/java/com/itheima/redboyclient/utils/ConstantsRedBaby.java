package com.itheima.redboyclient.utils;

public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.56.1:8080/RedBabyServer";
    //public static final String URL_SERVER = "http://192.168.16.28:8080/RedBabyServer";
    public static final String URL_TOPIC = URL_SERVER + "/topic";
    public static final String URL_REGIST =URL_SERVER + "/register";
    public static final String URL_HOME = URL_SERVER + "/home";
    public static final String URL_LOGIN = URL_SERVER + "/login";
    public static final String URL_NEWPRODUCT = URL_SERVER + "/newproduct";



    /**
     * topic请求码
     */
    public static final int REQUEST_CODE_TOPIC = 0;
    public static final int REQUEST_CODE_HOME = 1;
    public static final int REQUEST_CODE_LOGIN = 2;
    public static final int REQUEST_CODE_REGIST = 3;
    public static final int REQUEST_NEW_PRODUCT= 4;




}
