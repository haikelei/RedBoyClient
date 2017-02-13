package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by yudenghao on 2017/2/12.
 */

public class MyOrderResponse implements IResponse {


    /**
     * checkoutAddup : {"freight":10,"totalCount":5,"totalPoint":30,"totalPrice":450}
     * checkoutProm : ["促销信息一","促销信息二"]
     * deliveryList : [{"des":"周一至周五送货","type":1},{"des":"双休日及公众假期送货","type":2},{"des":"时间不限，工作日双休日及公众假期均可送货","type":3}]
     * paymentList : [{"des":"到付-现金","type":1},{"des":"到付-POS机","type":2},{"des":"支付宝","type":1}]
     * productList : [{"prodNum":3,"product":{"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}},{"prodNum":2,"product":{"id":2,"name":"粉色毛衣","pic":"/images/product/detail/q1.jpg","price":100,"productProperty":[{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"}]}}]
     * response : checkOut
     */

    public CheckoutAddupBean checkoutAddup;
    public String response;
    public List<String> checkoutProm;
    public List<DeliveryListBean> deliveryList;
    public List<DeliveryListBean> paymentList;
    public List<ProductListBean> productList;

    public CheckoutAddupBean getCheckoutAddup() {
        return checkoutAddup;
    }

    public void setCheckoutAddup(CheckoutAddupBean checkoutAddup) {
        this.checkoutAddup = checkoutAddup;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getCheckoutProm() {
        return checkoutProm;
    }

    public void setCheckoutProm(List<String> checkoutProm) {
        this.checkoutProm = checkoutProm;
    }

    public List<DeliveryListBean> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<DeliveryListBean> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<DeliveryListBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<DeliveryListBean> paymentList) {
        this.paymentList = paymentList;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class CheckoutAddupBean {
        /**
         * freight : 10
         * totalCount : 5
         * totalPoint : 30
         * totalPrice : 450
         */

        public int freight;
        public int totalCount;
        public int totalPoint;
        public int totalPrice;

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public void setTotalPoint(int totalPoint) {
            this.totalPoint = totalPoint;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class DeliveryListBean {
        /**
         * des : 周一至周五送货
         * type : 1
         */

        public String des;
        public int type;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class ProductListBean {
        /**
         * prodNum : 3
         * product : {"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}
         */

        public int prodNum;
        public ProductBean product;

        public int getProdNum() {
            return prodNum;
        }

        public void setProdNum(int prodNum) {
            this.prodNum = prodNum;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * id : 1
             * name : 韩版时尚蕾丝裙
             * pic : /images/product/detail/c3.jpg
             * price : 350
             * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]
             */

            public int id;
            public String name;
            public String pic;
            public int price;
            public List<ProductPropertyBean> productProperty;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public List<ProductPropertyBean> getProductProperty() {
                return productProperty;
            }

            public void setProductProperty(List<ProductPropertyBean> productProperty) {
                this.productProperty = productProperty;
            }

            public static class ProductPropertyBean {
                /**
                 * id : 1
                 * k : 颜色
                 * v : 红色
                 */

                public int id;
                public String k;
                public String v;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }
            }
        }
    }
}
