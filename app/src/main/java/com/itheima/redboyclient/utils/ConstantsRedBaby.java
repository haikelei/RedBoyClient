package com.itheima.redboyclient.utils;

public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.56.1:8080/RedBabyServer";
    public static final String URL_TOPIC = URL_SERVER + "/topic";
    public static final String URL_REGIST =URL_SERVER + "/register";
    public static final String URL_HOME = URL_SERVER + "/home";
    public static final String URL_GOODDETAIL = URL_SERVER + "/product";
    public static final String URL_FLASH = URL_SERVER + "/limitbuy";

    public static final String URL_SEARCH = URL_SERVER +"/search";
    public static final String URL_LOGIN = URL_SERVER +"/login";
    public static final String URL_SEARCH_RECOMMEND = URL_SEARCH +"/recommend";
    public static final String URL_BRAND = URL_SERVER + "/brand";
    public static final String URL_CATEGORY = URL_SERVER + "/category";
    public static final String URL_ADDRESSSLIST = URL_SERVER + "/addresslist";
    public static final String URL_COMMENT = URL_SERVER + "/product/comment";
    public static final String URL_ACCOUNT_CENTER = URL_SERVER + "/userinfo";
    public static final String URL_LOGOUT = URL_SERVER + "/logout";
    public static final String URL_NEWPRODUCT = URL_SERVER + "/newproduct";
    public static final String URL_HOTPRODUCT = URL_SERVER + "/hotproduct";
    public static final String URL_SHOPPING_CAR = URL_SERVER + "/cart";
    public static final String URL_HELP= URL_SERVER + "/help";
    public static final String URL_HELP_DETAIL= URL_SERVER + "/helpDetail";

    public static final String URL_BROWSING_HISTORY = URL_SERVER + "/product";

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
    public static final int REQUEST_CODE_FLASH= 10;
    public static final int REQUEST_CODE_ADDRESSSLIST= 20;
    public static final int REQUEST_CODE_GOODDETAIL = 10;
    public static final int REQUEST_CODE_SHOPPING_CAR = 11;
    public static final int REQUEST_CODE_FLASH = 12;
    public static final int REQUEST_NEW_PRODUCT= 15;
    public static final int REQUEST_HOT_PRODUCT= 16;
    public static final int REQUEST_CODE_HELP = 17;
    public static final int REQUEST_CODE_HELP_DETAIL = 18;
    public static final int REQUEST_CODE_COMMENT = 19;
    public static final int REQUEST_CODE_ACCOUNT_CENTER = 13;
    public static final int REQUEST_CODE_LOGOUT = 14;


}
