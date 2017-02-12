package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by gary on 2017/2/12.
 */

public class FavResponse implements IResponse{

    /**
     * response : addfavorites
     */

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
