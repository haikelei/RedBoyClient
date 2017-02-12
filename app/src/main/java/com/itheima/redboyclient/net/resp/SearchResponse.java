package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fee1in on 2017/2/10.
 */
public class SearchResponse implements IResponse,Serializable {

    /**
     * response : search
     * productList : [{"id":"1102539","name":"雅培金装","pic":"","marketPrice":"79","price":"78"},{"id":"1102539","name":"雅培金装","pic":"","marketPrice":"79","price":"78"}]
     * listCount : 15
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
         * id : 1102539
         * name : 雅培金装
         * pic :
         * marketPrice : 79
         * price : 78
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
