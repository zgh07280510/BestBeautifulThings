package com.lanou.bestbeautifulthings.designer.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.util.List;

/**
 * Created by dllo on 16/8/2.
 */
public class DesignerAttentionBean {
    @Column("id")
    int id;
    String label, name,  avatar,recommendImage;

    public DesignerAttentionBean() {
    }

    public DesignerAttentionBean(int id, String label, String name, String avatar, String recommendImage) {
        this.id = id;
        this.label = label;
        this.name = name;
        this.avatar = avatar;
        this.recommendImage = recommendImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRecommendImage() {
        return recommendImage;
    }

    public void setRecommendImage(String recommendImage) {
        this.recommendImage = recommendImage;
    }
}
