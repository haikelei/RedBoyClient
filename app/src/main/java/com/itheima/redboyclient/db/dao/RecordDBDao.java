package com.itheima.redboyclient.db.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.redboyclient.db.GoodSqliteOpenHelper;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

public class RecordDBDao {
    private GoodSqliteOpenHelper helper;
    private String tableName = GoodSqliteOpenHelper.TABLE_NAME;


    /**
     * 在构造方法里面初始化helper对象.
     *
     * @param context
     */
    public RecordDBDao(Context context) {
        helper = new GoodSqliteOpenHelper(context);
    }


    /**
     * 添加浏览商品到数据库
     *
     * @param productId 商品ID
     */
    public boolean add(int productId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoodSqliteOpenHelper.TYPE, ConstantsRedBaby.BROWSE_RECORD);
        values.put(GoodSqliteOpenHelper.PRODUCT_ID, productId);
        long result = db.insert(tableName, null, values);
        db.close();//关闭数据库释放资源
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单条浏览记录
     *
     * @param productId 商品ID
     */
    public boolean delete(int productId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = 0;
        String whereClause = GoodSqliteOpenHelper.TYPE + "=？," + GoodSqliteOpenHelper.PRODUCT_ID + "=?";
        result = db.delete(tableName, whereClause, new String[]{ConstantsRedBaby.BROWSE_RECORD, productId + ""});
        db.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除所有浏览记录
     */
    public boolean deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = 0;
        String whereClause = GoodSqliteOpenHelper.TYPE + "=？";
        result = db.delete(tableName, whereClause, new String[]{ConstantsRedBaby.BROWSE_RECORD});
        db.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取所有浏览记录
     *
     * @return
     */
    public List<String> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();//获取一个可读的数据库
        List<String> list = new ArrayList<>();
        Cursor cursor = db.query(tableName, new String[]{GoodSqliteOpenHelper.PRODUCT_ID}, GoodSqliteOpenHelper.TYPE + "=?", new java.lang.String[]{ConstantsRedBaby.BROWSE_RECORD}, null, null, null);
        while (cursor.moveToNext()) {
            String productId = cursor.getString(0);
            list.add(productId);
        }
        cursor.close();
        db.close();
        return list;
    }
}
