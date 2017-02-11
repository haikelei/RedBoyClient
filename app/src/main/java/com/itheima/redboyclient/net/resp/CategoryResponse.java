package com.itheima.redboyclient.net.resp;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　()
 * 　　  ( ) 　　( )
 * 　　  ( ) 　　( )
 * 　　┏━┛┻━━━━━━┛┻┓
 * 　　┃　　　━　　 ┃
 * 　　┃　┳┛　  ┗┳ ┃
 * 　　┃　　　┻　　 ┃
 * 　　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┗━━┣┓
 * 　　　　┃　　　　┏━━━━┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 */
public class CategoryResponse implements IResponse{


    /**
     * category : [{"id":1,"isLeafNode":false,"name":"妈妈专区","parentId":0,
     * "pic":"/images/category/ym.png","tag":"妈妈内衣  祛纹纤体"},{"id":2,"isLeafNode":false,
     * "name":"时尚女装","parentId":0,"pic":"/images/category/yf.png","tag":"时尚女装"},{"id":3,
     * "isLeafNode":false,"name":"宝宝用品","parentId":0,"pic":"/images/category/bb.png","tag":"奶粉辅食
     * 婴幼儿营养"},{"id":4,"isLeafNode":false,"name":"日常用品","parentId":0,"pic":"/images/category/wawa
     * .jpg"},{"id":5,"isLeafNode":false,"name":"儿童服饰","parentId":0,"pic":"/images/category/rj
     * .jpg"},{"id":6,"isLeafNode":false,"name":"儿童玩具","parentId":0,"pic":"/images/category/qu
     * .jpg"},{"id":11,"isLeafNode":false,"name":"妈妈个人用品","parentId":1,"pic":"/images/category/aa
     * .jpg","tag":""},{"id":12,"isLeafNode":false,"name":"孕妈妇服饰","parentId":1,
     * "pic":"/images/category/cc.jpg","tag":""},{"id":13,"isLeafNode":false,"name":"孕妇内衣",
     * "parentId":1,"pic":"/images/category/dd.jpg","tag":""},{"id":14,"isLeafNode":false,
     * "name":"时尚外套","parentId":2,"pic":"/images/category/18.jpg","tag":"0"},{"id":15,
     * "isLeafNode":false,"name":"时尚内搭","parentId":2,"pic":"/images/category/19.jpg","tag":"0"},
     * {"id":21,"isLeafNode":false,"name":"幼儿玩具","parentId":3,"pic":"/images/category/ee.jpg"},
     * {"id":22,"isLeafNode":false,"name":"儿童玩具","parentId":3,"pic":"/images/category/ff.jpg"},
     * {"id":23,"isLeafNode":false,"name":"儿童新款","parentId":5,"pic":"/images/category/25.jpg"},
     * {"id":33,"isLeafNode":false,"name":"餐具","parentId":4,"pic":"/images/category/fj.jpg"},
     * {"id":111,"isLeafNode":true,"name":"妈妈养生","parentId":11,"pic":"/images/category/mb.jpg",
     * "tag":""},{"id":112,"isLeafNode":true,"name":"孕妇洗发水","parentId":11,
     * "pic":"/images/category/mh.jpg"},{"id":113,"isLeafNode":true,"name":"孕妇服","parentId":13,
     * "pic":"/images/category/mi.jpg"},{"id":114,"isLeafNode":true,"name":"妈妈内衣","parentId":11,
     * "pic":"/images/category/mj.jpg"},{"id":115,"isLeafNode":true,"name":"孕妈用品","parentId":12,
     * "pic":"/images/category/ym.png","tag":"0"},{"id":116,"isLeafNode":true,"name":"妈妈护理",
     * "parentId":12,"pic":"/images/category/yf.png","tag":"0"},{"id":117,"isLeafNode":true,
     * "name":"孕妇服饰","parentId":12,"pic":"/images/category/ym.png","tag":"0"},{"id":118,
     * "isLeafNode":true,"name":"妈妈内衣","parentId":13,"pic":"/images/category/ym.png","tag":"0"},
     * {"id":119,"isLeafNode":true,"name":"洁牙护齿1","parentId":13,"pic":"/images/category/aa.jpg",
     * "tag":"0"},{"id":120,"isLeafNode":true,"name":"儿童玩具","parentId":21,
     * "pic":"/images/category/ca.jpg","tag":"0"},{"id":121,"isLeafNode":true,"name":"玩具车",
     * "parentId":21,"pic":"/images/category/cb.jpg","tag":"0"},{"id":122,"isLeafNode":true,
     * "name":"儿童玩具","parentId":22,"pic":"/images/category/ci.jpg","tag":"0"},{"id":123,
     * "isLeafNode":true,"name":"儿童玩具","parentId":22,"pic":"/images/category/cn.jpg","tag":"0"},
     * {"id":124,"isLeafNode":true,"name":"春秋新款","parentId":14,"pic":"/images/category/18.jpg",
     * "tag":"0"},{"id":125,"isLeafNode":true,"name":"时尚达人","parentId":14,
     * "pic":"/images/category/19.jpg","tag":"0"},{"id":126,"isLeafNode":true,"name":"女士内衣",
     * "parentId":15,"pic":"/images/category/20.jpg","tag":"0"},{"id":127,"isLeafNode":true,
     * "name":"女士外搭","parentId":15,"pic":"/images/category/21.jpg","tag":"0"},{"id":128,
     * "isLeafNode":true,"name":"孕妇理疗贴","parentId":11,"pic":"/images/category/ml.jpg"},{"id":129,
     * "isLeafNode":true,"name":"杯子","parentId":4,"pic":"/images/category/r.jpg"},{"id":130,
     * "isLeafNode":true,"name":"孕妇项链","parentId":11,"pic":"/images/category/mt.jpg"},{"id":131,
     * "isLeafNode":true,"name":"孕妇bra","parentId":11,"pic":"/images/category/my.jpg"},{"id":132,
     * "isLeafNode":true,"name":"韩版新款","parentId":14,"pic":"/images/category/22.jpg"},{"id":133,
     * "isLeafNode":true,"name":"春秋新款","parentId":14,"pic":"/images/category/23.jpg"},{"id":134,
     * "isLeafNode":true,"name":"新款内衣","parentId":15,"pic":"/images/category/25.jpg"},{"id":135,
     * "isLeafNode":true,"name":"女装","parentId":15,"pic":"/images/category/26.jpg"},{"id":136,
     * "isLeafNode":true,"name":"儿童娃娃","parentId":21,"pic":"/images/category/cv.jpg"},{"id":137,
     * "isLeafNode":true,"name":"维尼小熊","parentId":21,"pic":"/images/category/cw.jpg"},{"id":138,
     * "isLeafNode":true,"name":"小黄鸭","parentId":21,"pic":"/images/category/cy.jpg"},{"id":139,
     * "isLeafNode":true,"name":"小塔","parentId":22,"pic":"/images/category/cz.jpg"},{"id":140,
     * "isLeafNode":true,"name":"玩具枪","parentId":22,"pic":"/images/category/cu.jpg"},{"id":141,
     * "isLeafNode":true,"name":"韩版女装","parentId":14,"pic":"/images/category/34.jpg"},{"id":142,
     * "isLeafNode":true,"name":"外套","parentId":14,"pic":"/images/category/35.jpg"},{"id":143,
     * "isLeafNode":true,"name":"内衣","parentId":15,"pic":"/images/category/ft.jpg"},{"id":144,
     * "isLeafNode":true,"name":"杯子","parentId":33,"pic":"/images/category/r.jpg"},{"id":145,
     * "isLeafNode":true,"name":"餐具","parentId":33,"pic":"/images/category/re.jpg"},{"id":146,
     * "isLeafNode":true,"name":"餐具叉子","parentId":33,"pic":"/images/category/rg.jpg"},{"id":147,
     * "isLeafNode":true,"name":"玩具","parentId":33,"pic":"/images/category/rh.jpg"},{"id":148,
     * "isLeafNode":true,"name":"老虎钳","parentId":33,"pic":"/images/category/ry.jpg"},{"id":149,
     * "isLeafNode":true,"name":"电饭锅","parentId":33,"pic":"/images/category/rj.jpg"},{"id":150,
     * "isLeafNode":true,"name":"衣服","parentId":154,"pic":"/images/category/qe.jpg"},{"id":151,
     * "isLeafNode":false,"name":"儿童裙子","parentId":5,"pic":"/images/category/qi.jpg"},{"id":152,
     * "isLeafNode":true,"name":"新款衣服","parentId":154,"pic":"/images/category/qo.jpg"},{"id":153,
     * "isLeafNode":true,"name":"新款衣服","parentId":154,"pic":"/images/category/qr.jpg"},{"id":154,
     * "isLeafNode":false,"name":"宝宝新款","parentId":5,"pic":"/images/category/qt.jpg"}]
     * response : category
     */

    private String response;
    private List<CategoryBean> category;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {
        /**
         * id : 1
         * isLeafNode : false
         * name : 妈妈专区
         * parentId : 0
         * pic : /images/category/ym.png
         * tag : 妈妈内衣  祛纹纤体
         */

        private int id;
        private boolean isLeafNode;
        private String name;
        private int parentId;
        private String pic;
        private String tag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsLeafNode() {
            return isLeafNode;
        }

        public void setIsLeafNode(boolean isLeafNode) {
            this.isLeafNode = isLeafNode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
