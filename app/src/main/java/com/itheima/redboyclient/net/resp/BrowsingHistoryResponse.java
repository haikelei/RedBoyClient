package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by sjk on 2017/2/10.
 */

public class BrowsingHistoryResponse implements IResponse {

    /**
     * product : {"available":true,"bigPic":["/images/product/detail/bigcar1.jpg","/images/product/detail/bigcar2.jpg","/images/product/detail/bigcar3.jpg","/images/product/detail/bigcar4.jpg"],"buyLimit":10,"commentCount":10,"id":1,"inventoryArea":"全国","leftTime":18000,"limitPrice":200,"marketPrice":500,"name":"韩版时尚蕾丝裙","pics":["/images/product/detail/c3.jpg","/images/product/detail/c4.jpg","/images/product/detail/c1.jpg","/images/product/detail/c2.jpg"],"price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":5}
     * response : product
     */

    private ProductBean product;
    private String response;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class ProductBean {
        /**
         * commentCount : 10
         * id : 1
         * marketPrice : 500
         * name : 韩版时尚蕾丝裙
         * pics : ["/images/product/detail/c3.jpg","/images/product/detail/c4.jpg","/images/product/detail/c1.jpg","/images/product/detail/c2.jpg"]
         * price : 350
         */


        private int commentCount;
        private int id;
        private int marketPrice;
        private String name;
        private int price;
        private List<String> pics;


        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }


    }
}
