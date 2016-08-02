package com.lanou.bestbeautifulthings.mine;

/**
 * Created by dllo on 16/8/2.
 */
public class UserInfo {
    private String userQQImageUrl,userSinaImagUrl;
    private String userQQName,userSinaName;
    private String userQQId,userSinaId;

    public UserInfo() {

    }

    public UserInfo(String userQQImageUrl, String userSinaImagUrl, String userQQName, String userSinaName, String userQQId, String userSinaId) {
        this.userQQImageUrl = userQQImageUrl;
        this.userSinaImagUrl = userSinaImagUrl;
        this.userQQName = userQQName;
        this.userSinaName = userSinaName;
        this.userQQId = userQQId;
        this.userSinaId = userSinaId;
    }

    public String getUserQQImageUrl() {
        return userQQImageUrl;
    }

    public void setUserQQImageUrl(String userQQImageUrl) {
        this.userQQImageUrl = userQQImageUrl;
    }

    public String getUserSinaImagUrl() {
        return userSinaImagUrl;
    }

    public void setUserSinaImagUrl(String userSinaImagUrl) {
        this.userSinaImagUrl = userSinaImagUrl;
    }

    public String getUserQQName() {
        return userQQName;
    }

    public void setUserQQName(String userQQName) {
        this.userQQName = userQQName;
    }

    public String getUserSinaName() {
        return userSinaName;
    }

    public void setUserSinaName(String userSinaName) {
        this.userSinaName = userSinaName;
    }

    public String getUserQQId() {
        return userQQId;
    }

    public void setUserQQId(String userQQId) {
        this.userQQId = userQQId;
    }

    public String getUserSinaId() {
        return userSinaId;
    }

    public void setUserSinaId(String userSinaId) {
        this.userSinaId = userSinaId;
    }
}
