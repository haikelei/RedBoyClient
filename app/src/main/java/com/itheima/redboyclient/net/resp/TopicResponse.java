package com.itheima.redboyclient.net.resp;


import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.PrintUtil;

import java.util.List;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p/>
 * Created by Seny on 2015/11/30.
 *
 *
 * 用于封装topic信息
 */
public class TopicResponse implements IResponse {

    /**
     * response的类型，topic？home？error？或者其他
     */
    public String response;
    /**
     * id : 10000
     * name : 雅培金装0
     * pic : images/image1.png
     */

    public List<TopicEntity> topic;


    /**
     * Topic实体
     */
    public static class TopicEntity {
        public int id;
        public String name;
        public String pic;

        @Override
        public String toString() {
            return "TopicEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TopicResponse{" +
                "response='" + response + '\'' +
                ", topic=" + PrintUtil.printList(topic) +
                '}';
    }

}
