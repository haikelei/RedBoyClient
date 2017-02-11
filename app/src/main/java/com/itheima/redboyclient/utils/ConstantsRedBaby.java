package com.itheima.redboyclient.utils;

public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.56.1:8080/RedBabyServer";
    public static final String URL_TOPIC = URL_SERVER + "/topic";
    public static final String URL_REGIST =URL_SERVER + "/register";
    public static final String URL_HOME = URL_SERVER + "/home";
    public static final String URL_GOODDETAIL = URL_SERVER + "/product";
    public static final String URL_FLASH = URL_SERVER + "/limitbuy";

    public static final String URL_SEARCH = URL_SERVER + "/search";
    public static final String URL_LOGIN = URL_SERVER + "/login";
    public static final String URL_SEARCH_RECOMMEND = URL_SEARCH + "/recommend";
    public static final String URL_BRAND = URL_SERVER + "/brand";
    public static final String URL_CATEGORY = URL_SERVER + "/category";


    public static final String NO_HISTORY = "没有搜索记录";
    public static final String SHOPPING_CAR = "shoppingCar";
    public static final String BROWSE_RECORD = "浏览记录";


    /**
     * topic请求码
     */
    public static final int REQUEST_CODE_HOME = 0;
    public static final int REQUEST_CODE_RECOMMEND = 1;
    public static final int REQUEST_CODE_CATEGORY = 2;
    public static final int REQUEST_CODE_SHOPPING = 3;
    //TODO 主页五个fragment使用0-4；

    public static final int REQUEST_CODE_TOPIC = 5;
    public static final int REQUEST_CODE_LOGIN = 6;
    public static final int REQUEST_CODE_REGIST = 7;
    public static final int REQUEST_CODE_BRAND = 8;
    public static final int REQUEST_CODE_SEARCH = 9;
    public static final int REQUEST_CODE_GOODDETAIL = 10;
    public static final int REQUEST_CODE_FLASH = 12;

}
