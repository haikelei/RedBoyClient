package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

public class UserInfoResponse implements IResponse{

    /**
     * response : userInfo
     * userInfo : {"bonus":0,"level":"普通会员","userId":30505,"orderCount":"20","favoritesCount":"12"}
     */

    public String response;
    /**
     * bonus : 0
     * level : 普通会员
     * userId : 30505
     * orderCount : 20
     * favoritesCount : 12
     */

    public UserInfoEntity userInfo;

    public static class UserInfoEntity {
        public int bonus;
        public String level;
        public int userId;
        public String orderCount;
        public String favoritesCount;

        @Override
        public String toString() {
            return "UserInfoEntity{" +
                    "bonus=" + bonus +
                    ", level='" + level + '\'' +
                    ", userId=" + userId +
                    ", orderCount='" + orderCount + '\'' +
                    ", favoritesCount='" + favoritesCount + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "response='" + response + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
