package com.lanou.bestbeautifulthings.mine;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/8/2.
 */
public class UserSinaBean extends BmobObject {

    private String SinaUserId;
    private String SinaUserName;
    private String SinaUserIcon;

    public String getSinaUserId() {
        return SinaUserId;
    }

    public void setSinaUserId(String sinaUserId) {
        SinaUserId = sinaUserId;
    }

    public String getSinaUserName() {
        return SinaUserName;
    }

    public void setSinaUserName(String sinaUserName) {
        SinaUserName = sinaUserName;
    }

    public String getSinaUserIcon() {
        return SinaUserIcon;
    }

    public void setSinaUserIcon(String sinaUserIcon) {
        SinaUserIcon = sinaUserIcon;
    }
}
