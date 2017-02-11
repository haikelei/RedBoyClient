package com.itheima.redboyclient.domain;

/**
 * Created by fee1in on 2017/2/10.
 */
public class Goods {
    private int productId;
    private int productPropertyId;
    private int productNum;

    public Goods(int productId, int productPropertyId, int productNum) {
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

    public int getProductPropertyId() {
        return productPropertyId;
    }

    public void setProductPropertyId(int productPropertyId) {
        this.productPropertyId = productPropertyId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "productId=" + productId +
                ", productPropertyId=" + productPropertyId +
                ", productNum=" + productNum +
                '}';
    }
}
