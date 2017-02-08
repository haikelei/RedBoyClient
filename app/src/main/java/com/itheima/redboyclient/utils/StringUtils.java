package com.itheima.redboyclient.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/11/23.
 */
public class StringUtils {

    public static boolean checkUsername(String username){
        if(TextUtils.isEmpty(username)){
            return false;
        }

        //长度为3-20位，必须以字母开头
        return username.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    public static boolean checkPwd(String pwd){
        if(TextUtils.isEmpty(pwd)){
            return false;
        }
        //3-20位字母
        return pwd.matches("^[0-9]{3,20}$");
    }

    /**
     * 获取首字母
     * @param contact
     * @return
     */
    public static  String getInitial(String contact){
        if (TextUtils.isEmpty(contact)){
            return contact;
        }else {
            return contact.substring(0,1).toUpperCase();
        }
    }
}
