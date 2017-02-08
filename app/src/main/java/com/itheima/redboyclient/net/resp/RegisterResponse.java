package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by sjk on 2017/2/7.
 */

public class RegisterResponse implements IResponse {


    /**
     * response : register
     * userInfo : {"userid":"202847"}
     * error : 该用户名已经被注册过了
     * error_code : 1532
     */

    private String response;
    private UserInfoBean userInfo;
    private String error;
    private String error_code;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public static class UserInfoBean {
        /**
         * userid : 202847
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "response='" + response + '\'' +
                ", userInfo=" + userInfo +
                ", error='" + error + '\'' +
                ", error_code='" + error_code + '\'' +
                '}';
    }
}
