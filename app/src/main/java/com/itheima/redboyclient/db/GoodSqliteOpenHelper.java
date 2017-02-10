package com.itheima.redboyclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itheima.redboyclient.App;

public class GoodSqliteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = App.getUserId()+".db";
    //使用登陆用户名作为表名
    public static final String TABLE_NAME = "product";
    private static final int VERSION = 1;
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_PROPERTY_ID = "product_property_id";
    public static final String PRODUCT_NUM = "product_num";
    public static final String TYPE = "type";

    private GoodSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //TODO 测试

    }

    public GoodSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //初始化表结构
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("onCreate", "onCreate: "+TABLE_NAME);
        db.execSQL("create table " + TABLE_NAME + "(_id integer primary key," + TYPE + " varchar(20),"
                +PRODUCT_ID + " varchar(20)," + PRODUCT_NUM + " varchar(20)," + PRODUCT_PROPERTY_ID + " varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}