package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by gary on 2017/2/10.
 */

public class CommentResponse implements IResponse{


    /**
     * comment : [{"content":"裙子不错裙子不错裙子不错裙子不错裙子不错裙子不错裙子","time":2014,"title":"裙子","username":"赵刚"},{"content":"裙子不错裙子不错裙子不错裙子不错裙子不错裙子不错裙子","time":2015,"title":"裙子","username":"李里"}]
     * response : productComment
     */

    private String response;
    private List<CommentBean> comment;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        /**
         * content : 裙子不错裙子不错裙子不错裙子不错裙子不错裙子不错裙子
         * time : 2014
         * title : 裙子
         * username : 赵刚
         */

        private String content;
        private int time;
        private String title;
        private String username;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

