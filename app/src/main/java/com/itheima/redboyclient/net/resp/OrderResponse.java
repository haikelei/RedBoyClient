package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
public class OrderResponse implements IResponse {
    /**
     * response : orderList
     * orderList : [{"orderId":"260092","status":"未处理","time":"1439528260115","price":"208","flag":"1"},{"orderId":"171835","status":"未处理","time":"1439529171852","price":"\u201c208\u201d","flag":"1"},{"orderId":" 879495 ","status":"未处理","time":" 1449037879509 ","price":"\u201c450\u201d","flag":"1"}]
     */

    private String response;
    private List<OrderListBean> orderList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * orderId : 260092
         * status : 未处理
         * time : 1439528260115
         * price : 208
         * flag : 1
         */

        private String orderId;
        private String status;
        private String time;
        private String price;
        private String flag;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
