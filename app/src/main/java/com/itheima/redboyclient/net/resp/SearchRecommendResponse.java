package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by fee1in on 2017/2/8.
 */
public class SearchRecommendResponse implements IResponse {
    /**
     * response : searchrecommend
     * searchKeywords : ["adidas","361","nike"]
     */

    private String response;
    private List<String> searchKeywords;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
