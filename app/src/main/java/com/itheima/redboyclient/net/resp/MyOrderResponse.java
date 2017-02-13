package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by yudenghao on 2017/2/12.
 */

public class MyOrderResponse implements IResponse {

    /**
     * prom : ["促销信息一","促销信息二"]
     * checkoutAddup : {"freight":10,"totalPoint":30,"totalCount":5,"totalPrice":1250}
     * productList : [{"prodNum":3,"product":{"id":1,"pic":"/images/product/detail/c3.jpg",
     * "name":"韩版时尚蕾丝裙","price":350,"productProperty":[{"id":0,"k":"颜色","v":"红色"},
     * {"id":1,"k":"颜色","v":"绿色"}]}},{"prodNum":2,"product":
     * {"id":2,"pic":"/images/product/detail/q1.jpg","name":"粉色毛衣","price":100,"productProperty":
     * [{"id":0,"k":"颜色","v":"绿色"},{"id":1,"k":"尺码","v":"M"}]}}]
     * response : orderDetail
     * addressInfo : {"addressArea":"密云县","id":139,"addressDetail":"街道口地铁c口","name":"itcast"}
     * orderInfo : {"flag":3,"status":"未处理","orderId":"098593","time":"1449107098604"}
     * deliveryInfo : {"type":"1"}
     * invoiceInfo : {"invoiceContent":"传智慧播客教育科技有限公司","invoiceTitle":"传智慧播客教育科技有限公司"}
     * paymentInfo : {"type":1}
     */

    public CheckoutAddupBean checkoutAddup;
    public String response;
    public AddressInfoBean addressInfo;
    public OrderInfoBean orderInfo;
    public DeliveryInfoBean deliveryInfo;
    public InvoiceInfoBean invoiceInfo;
    public PaymentInfoBean paymentInfo;
    public List<String> prom;
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

    public AddressInfoBean getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfoBean addressInfo) {
        this.addressInfo = addressInfo;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public DeliveryInfoBean getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfoBean deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public InvoiceInfoBean getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfoBean invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public PaymentInfoBean getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoBean paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<String> getProm() {
        return prom;
    }

    public void setProm(List<String> prom) {
        this.prom = prom;
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
         * totalPoint : 30
         * totalCount : 5
         * totalPrice : 1250
         */

        public int freight;
        public int totalPoint;
        public int totalCount;
        public int totalPrice;

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public void setTotalPoint(int totalPoint) {
            this.totalPoint = totalPoint;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class AddressInfoBean {
        /**
         * addressArea : 密云县
         * id : 139
         * addressDetail : 街道口地铁c口
         * name : itcast
         */

        public String addressArea;
        public int id;
        public String addressDetail;
        public String name;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class OrderInfoBean {
        /**
         * flag : 3
         * status : 未处理
         * orderId : 098593
         * time : 1449107098604
         */

        public int flag;
        public String status;
        public String orderId;
        public String time;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class DeliveryInfoBean {
        /**
         * type : 1
         */

        public String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class InvoiceInfoBean {
        /**
         * invoiceContent : 传智慧播客教育科技有限公司
         * invoiceTitle : 传智慧播客教育科技有限公司
         */

        public String invoiceContent;
        public String invoiceTitle;

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }
    }

    public static class PaymentInfoBean {
        /**
         * type : 1
         */

        public int type;

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
         * product : {"id":1,"pic":"/images/product/detail/c3.jpg","name":"韩版时尚蕾丝裙","price":350,"productProperty":[{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"颜色","v":"绿色"}]}
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
             * pic : /images/product/detail/c3.jpg
             * name : 韩版时尚蕾丝裙
             * price : 350
             * productProperty : [{"id":0,"k":"颜色","v":"红色"},{"id":1,"k":"颜色","v":"绿色"}]
             */

            public int id;
            public String pic;
            public String name;
            public int price;
            public List<ProductPropertyBean> productProperty;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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

            public List<ProductPropertyBean> getProductProperty() {
                return productProperty;
            }

            public void setProductProperty(List<ProductPropertyBean> productProperty) {
                this.productProperty = productProperty;
            }

            public static class ProductPropertyBean {
                /**
                 * id : 0
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
