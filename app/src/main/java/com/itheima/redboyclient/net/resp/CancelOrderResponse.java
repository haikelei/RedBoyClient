package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yudenghao on 2017/2/13.
 */

public class CancelOrderResponse implements IResponse{


    /**
     * response : orderCancel
     */

    public String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
