package com.itheima.redboyclient.utils;

import android.widget.Toast;

import static com.itheima.redboyclient.App.application;

/**
 * Created by sjk on 2017/1/3.
 */

public class ToastUtil {
    private static Toast toast;
    public static void showToast(String  text){
        if (toast==null){
            toast = Toast.makeText(application.getApplicationContext(),text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
}
