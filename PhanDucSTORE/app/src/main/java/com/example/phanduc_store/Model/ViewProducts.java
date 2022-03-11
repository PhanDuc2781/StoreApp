package com.example.phanduc_store.Model;

import android.view.View;

import java.io.Serializable;

public class ViewProducts implements Serializable {
    String name ;
    String descriptions ;
    String type ;
    String starRate;
    String img_Url ;
    int price ;

    public ViewProducts(){}

    public ViewProducts(String name, String descriptions, String type, String starRate, String img_Url, int price) {
        this.name = name;
        this.descriptions = descriptions;
        this.type = type;
        this.starRate = starRate;
        this.img_Url = img_Url;
        this.price = price;
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

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getImg_Url() {
        return img_Url;
    }

    public void setImg_Url(String img_Url) {
        this.img_Url = img_Url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
