package com.lanou.bestbeautifulthings.designer;

import com.lanou.bestbeautifulthings.base.MyApp;
import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/8/2.
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm;
    private LiteOrm liteOrm;

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    private SingleLiteOrm() {
       liteOrm = LiteOrm.newCascadeInstance(MyApp.getContext(),"" +
               "Designer.db");

    }

    public static SingleLiteOrm getSingleLiteOrm() {
        if (singleLiteOrm == null) {
            synchronized (SingleLiteOrm.class) {
                if (singleLiteOrm == null) {
                    singleLiteOrm = new SingleLiteOrm();
                }
            }
        }

        return singleLiteOrm;
    }


}
