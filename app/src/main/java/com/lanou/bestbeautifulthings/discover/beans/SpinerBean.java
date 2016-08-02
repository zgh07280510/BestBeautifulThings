package com.lanou.bestbeautifulthings.discover.beans;

import java.util.List;

/**
 * Created by dllo on 16/7/30.
 */
public class SpinerBean {

    private DataBean data;
    /**
     * data : {"categories":[{"sub_categories":[{"id":51,"name":"功能包"},{"id":32,"name":"双肩包"},{"id":10,"name":"旅行箱"},{"id":9,"name":"钱包"},{"id":8,"name":"手拿包"},{"id":7,"name":"手提包"},{"id":6,"name":"斜挎包"},{"id":5,"name":"单肩包"}],"id":1,"name":"包袋"},{"sub_categories":[{"id":14,"name":"高跟鞋"},{"id":49,"name":"短靴"},{"id":48,"name":"长靴"},{"id":38,"name":"球鞋"},{"id":16,"name":"拖鞋"},{"id":15,"name":"平底鞋"},{"id":11,"name":"凉鞋"}],"id":2,"name":"鞋履"},{"sub_categories":[{"id":24,"name":"项链"},{"id":23,"name":"耳环"},{"id":22,"name":"戒指"},{"id":21,"name":"手链"},{"id":20,"name":"手镯"}],"id":3,"name":"首饰"},{"sub_categories":[{"id":53,"name":"手表"},{"id":52,"name":"袖扣"},{"id":45,"name":"胸针"},{"id":37,"name":"领带"},{"id":36,"name":"发饰"},{"id":35,"name":"长筒袜"},{"id":34,"name":"贴纸"},{"id":33,"name":"雨伞"},{"id":31,"name":"手套"},{"id":30,"name":"帽子"},{"id":29,"name":"腰带"},{"id":28,"name":"香水"},{"id":27,"name":"丝巾"},{"id":26,"name":"围巾"},{"id":25,"name":"墨镜"}],"id":4,"name":"配饰"},{"id":54,"name":"其他"}]}
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
        /**
         * sub_categories : [{"id":51,"name":"功能包"},{"id":32,"name":"双肩包"},{"id":10,"name":"旅行箱"},{"id":9,"name":"钱包"},{"id":8,"name":"手拿包"},{"id":7,"name":"手提包"},{"id":6,"name":"斜挎包"},{"id":5,"name":"单肩包"}]
         * id : 1
         * name : 包袋
         */

        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private int id;
            private String name;
            /**
             * id : 51
             * name : 功能包
             */

            private List<SubCategoriesBean> sub_categories;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SubCategoriesBean> getSub_categories() {
                return sub_categories;
            }

            public void setSub_categories(List<SubCategoriesBean> sub_categories) {
                this.sub_categories = sub_categories;
            }

            public static class SubCategoriesBean {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
