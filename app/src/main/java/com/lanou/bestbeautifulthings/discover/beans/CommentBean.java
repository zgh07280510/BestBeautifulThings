package com.lanou.bestbeautifulthings.discover.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/8/3.
 */
public class CommentBean extends BmobObject {
    private String userIcon;
    private String userName;
    private String comment;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
