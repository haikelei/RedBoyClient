package com.itheima.redboyclient.db.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.itheima.redboyclient.db.GoodSqliteOpenHelper;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBDao {
    private GoodSqliteOpenHelper helper;
    String whereClause = GoodSqliteOpenHelper.TYPE + "=? and " + GoodSqliteOpenHelper.PRODUCT_ID + "=? and "
            + GoodSqliteOpenHelper.PRODUCT_PROPERTY_ID + "=?";
    private String tableName = GoodSqliteOpenHelper.TABLE_NAME;
    ;

    /**
     * 在构造方法里面初始化helper对象.
     *
     * @param context
     */
    public ShoppingDBDao(Context context) {
        helper = new GoodSqliteOpenHelper(context);
    }


    /**
     * 增加商品到数据库
     *
     * @param goods 商品信息
     */
    public boolean add(Goods goods) {
        if (isHava(goods)) {
            return update(goods);
        }
        return addGoods(goods);
    }



    private boolean addGoods(Goods goods) {
        ContentValues values = new ContentValues();
        values.put(GoodSqliteOpenHelper.TYPE, ConstantsRedBaby.SHOPPING_CAR);
        values.put(GoodSqliteOpenHelper.PRODUCT_ID, goods.getProductId());
        values.put(GoodSqliteOpenHelper.PRODUCT_PROPERTY_ID, goods.getProductPropertyId());
        values.put(GoodSqliteOpenHelper.PRODUCT_NUM, goods.getProductNum());
        SQLiteDatabase db = helper.getWritableDatabase();
        long result = db.insert(tableName, null, values);
        db.close();//关闭数据库释放资源
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 删除指定的商品
     *
     * @param goods 商品信息
     * @return
     */
    public boolean delete(Goods goods) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = 0;
        result = db.delete(tableName, whereClause, newString(goods));
        db.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改数据库的商品信息
     *
     * @param goods 商品信息
     * @return
     */
    public boolean update(Goods goods) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = 0;
        values.put(GoodSqliteOpenHelper.PRODUCT_NUM, goods.getProductNum());
        result = db.update(tableName, values, whereClause, newString(goods));
        db.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 查看用户购物车是否包含此商品
     *
     * @return
     */
    private boolean isHava(Goods goods) {
        SQLiteDatabase db = helper.getReadableDatabase();//获取一个可读的数据库
        boolean result = false;
        Cursor cursor = db.query(tableName, new String[]{GoodSqliteOpenHelper.PRODUCT_NUM}, whereClause, newString(goods), null, null, null);
        if (cursor.moveToNext()) {
           result = true;
        }
        cursor.close();
        db.close();
        return result;

    }


    /**
     * 获取此用户购物车的全部商品信息
     *
     * @return
     */
    public List<Goods> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();//获取一个可读的数据库
        List<Goods> goodsList = new ArrayList<>();
        Cursor cursor = db.query(tableName, new String[]{GoodSqliteOpenHelper.PRODUCT_ID, GoodSqliteOpenHelper.PRODUCT_PROPERTY_ID, GoodSqliteOpenHelper.PRODUCT_NUM}, GoodSqliteOpenHelper.TYPE + "=?", new String[]{ConstantsRedBaby.SHOPPING_CAR}, null, null, null);
        while (cursor.moveToNext()) {
            int productId = Integer.parseInt(cursor.getString(0));
            String productPropertyId = cursor.getString(1);
            int productNum = Integer.parseInt(cursor.getString(2));
            Goods goods = new Goods(productId, productPropertyId, productNum);
            goodsList.add(goods);
        }
        cursor.close();
        db.close();
        return goodsList;
    }

    @NonNull
    private String[] newString(Goods goods) {
        return new String[]{ConstantsRedBaby.SHOPPING_CAR, goods.getProductId() + "", goods.getProductPropertyId()};
    }
}
