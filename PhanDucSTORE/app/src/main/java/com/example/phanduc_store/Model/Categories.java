package com.example.phanduc_store.Model;

import java.io.Serializable;

public class Categories {
    String name ;
    String descriptions ;
    String type;
    String img_Url ;

    public Categories(){}

    public Categories(String name, String descriptions, String type, String img_Url) {
        this.name = name;
        this.descriptions = descriptions;
        this.type = type;
        this.img_Url = img_Url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_Url() {
        return img_Url;
    }

    public void setImg_Url(String img_Url) {
        this.img_Url = img_Url;
    }
}
