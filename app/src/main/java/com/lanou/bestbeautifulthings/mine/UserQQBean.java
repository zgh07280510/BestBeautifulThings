package com.lanou.bestbeautifulthings.mine;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/8/3.
 */
public class UserQQBean extends BmobObject {
    private String QQUserId;
    private String QQUserName;
    private String QQUserIcon;

    public String getQQUserId() {
        return QQUserId;
    }

    public void setQQUserId(String QQUserId) {
        this.QQUserId = QQUserId;
    }

    public String getQQUserName() {
        return QQUserName;
    }

    public void setQQUserName(String QQUserName) {
        this.QQUserName = QQUserName;
    }

    public String getQQUserIcon() {
        return QQUserIcon;
    }

    public void setQQUserIcon(String QQUserIcon) {
        this.QQUserIcon = QQUserIcon;
    }
}
