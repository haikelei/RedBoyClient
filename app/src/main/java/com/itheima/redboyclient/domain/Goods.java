package com.itheima.redboyclient.domain;

/**
 * Created by fee1in on 2017/2/10.
 */
public class Goods {
    private int productId;
    private String productPropertyId;
    private int productNum;

    public Goods() {
    }

    public void setProductPropertyId(String productPropertyId) {
        this.productPropertyId = productPropertyId;
    }

    public String getProductPropertyId() {

        return productPropertyId;
    }

    public Goods(int productId, String productPropertyId, int productNum) {

        this.productId = productId;
        this.productPropertyId = productPropertyId;
        this.productNum = productNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }


}
