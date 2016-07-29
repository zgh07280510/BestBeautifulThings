package com.lanou.bestbeautifulthings.designer.bean;

import java.util.List;

/**
 * Created by dllo on 16/7/27.
 */
public class DesignerInformationBean {

    /**
     * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/6/16/fd6a7966-a76c-446c-b0f2-127fe397a1ed_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/138dea1e-8648-4ada-b421-8b896f06b0dd_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/555bfcb5-31da-4d73-b3d2-c81b6bc01ed5_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/e59896a4-9d68-4c87-88b2-3ec1493d5cbf_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/be709a75-d308-4c80-bed6-edbe06111eb3_1000x1000.jpeg"]
     * city : 西雅图
     * concept : 独特的并且与当地风俗景色相适宜的设计能够让你的产品鹤立鸡群
     * article_num : 0
     * name : Scott & Brittany Freeman
     * product_num : 10
     * label : Freeman 联合创始人
     * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/6/16/0e121207-6819-4557-91d4-9b4c335780d2.jpg
     * is_followed : 0
     * id : 27
     * description : Scott Freeman 起初是一位木匠，一次心血来潮想做一款经典雨衣（其实就是因为西雅图经常下雨），于是就和妻子把厨房改造成了缝衣间，后来也制作夹克饰品等，风格偏古典，舒适和实用。
     */

    private DataBean data;
    /**
     * data : {"introduce_images":["http://dstatic.zuimeia.com/common/image/2016/6/16/fd6a7966-a76c-446c-b0f2-127fe397a1ed_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/138dea1e-8648-4ada-b421-8b896f06b0dd_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/555bfcb5-31da-4d73-b3d2-c81b6bc01ed5_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/e59896a4-9d68-4c87-88b2-3ec1493d5cbf_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/6/16/be709a75-d308-4c80-bed6-edbe06111eb3_1000x1000.jpeg"],"city":"西雅图","concept":"独特的并且与当地风俗景色相适宜的设计能够让你的产品鹤立鸡群","article_num":0,"name":"Scott & Brittany Freeman","product_num":10,"label":"Freeman 联合创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/16/0e121207-6819-4557-91d4-9b4c335780d2.jpg","is_followed":0,"id":27,"description":"Scott Freeman 起初是一位木匠，一次心血来潮想做一款经典雨衣（其实就是因为西雅图经常下雨），于是就和妻子把厨房改造成了缝衣间，后来也制作夹克饰品等，风格偏古典，舒适和实用。"}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private String city;
        private String concept;
        private int article_num;
        private String name;
        private int product_num;
        private String label;
        private String avatar_url;
        private int is_followed;
        private int id;
        private String description;
        private List<String> introduce_images;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConcept() {
            return concept;
        }

        public void setConcept(String concept) {
            this.concept = concept;
        }

        public int getArticle_num() {
            return article_num;
        }

        public void setArticle_num(int article_num) {
            this.article_num = article_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(int is_followed) {
            this.is_followed = is_followed;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getIntroduce_images() {
            return introduce_images;
        }

        public void setIntroduce_images(List<String> introduce_images) {
            this.introduce_images = introduce_images;
        }
    }
}
