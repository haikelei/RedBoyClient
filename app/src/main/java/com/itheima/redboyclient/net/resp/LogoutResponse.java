package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yudenghao on 2017/2/12.
 */

public class LogoutResponse implements IResponse {


    /**
     * response : logout
     */

    public String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LogoutResponse{" +
                "response='" + response + '\'' +
                '}';
    }
}
