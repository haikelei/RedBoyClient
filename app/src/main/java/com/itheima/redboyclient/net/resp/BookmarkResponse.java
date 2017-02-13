package com.itheima.redboyclient.net.resp;

import com.itheima.redboyclient.activities.BookmarksActivity;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
public class BookmarkResponse implements IResponse {

    /**
     * response : favorites
     * productList : [{"id":"1","name":"韩版时尚蕾丝裙","pic":"","marketPrice":"500","price":"350"},{"id":"2","name":"粉色毛衣","pic":"","marketPrice":"79","price":"78"}]
     * listCount : 2
     */

    private String response;
    private String listCount;
    private List<ProductListBean> productList;



    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        /**
         * id : 1
         * name : 韩版时尚蕾丝裙
         * pic :
         * marketPrice : 500
         * price : 350
         */

        private String id;
        private String name;
        private String pic;
        private String marketPrice;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
