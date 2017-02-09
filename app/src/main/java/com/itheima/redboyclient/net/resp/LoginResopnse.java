package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yudenghao on 2017/2/7.
 */

public class LoginResopnse implements IResponse {

    /**
     * response : login
     * userInfo : {"userid":"154636"}
     */

    public String response;
    public UserInfoBean userInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userid : 154636
         */

        public String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
