package com.itheima.redboyclient.utils;

import android.text.TextUtils;

import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;

import java.util.List;

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

    public static String getSku(List<Goods> selectedGoodsList) {
        StringBuilder sku = new StringBuilder();
        int size = selectedGoodsList.size();
        for (int i = 0; i < size; i++) {
            Goods goods = selectedGoodsList.get(i);
            int productId = goods.getProductId();
            int productNum = goods.getProductNum();
            String productPropertyId = goods.getProductPropertyId();
            sku.append(productId)
                    .append(":")
                    .append(productNum)
                    .append(":")
                    .append(productPropertyId);
            if (i != size - 1) {

                sku.append("|");
            }
        }
        return sku.toString();
    }
    public static Goods getGoods(ShoppingCarResponse.CartBean cartBean) {
        //获取每个商品的数量
        int prodNum = cartBean.getProdNum();
        ShoppingCarResponse.CartBean.ProductBean product = cartBean.getProduct();
        //获取商品ID
        int id = product.getId();
        //获取商品属性
        List<ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean> productProperty = product.getProductProperty();
        //拼接商品属性
        StringBuilder productPropertyId = new StringBuilder();
        for (int j = 0; ; j++) {
            ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean productPropertyBean = productProperty.get(j);
            int PropertyId = productPropertyBean.getId();
            productPropertyId.append(PropertyId);
            if (j == productProperty.size() - 1) {
                break;
            }
            productPropertyId.append(",");
        }
        Goods goods = new Goods();
        goods.setProductId(id);
        goods.setProductNum(prodNum);
        goods.setProductPropertyId(productPropertyId.toString());
        return goods;
    }
}
