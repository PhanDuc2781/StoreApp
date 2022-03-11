package com.example.phanduc_store.Model;

import java.io.Serializable;

public class Recommends implements Serializable {
    String name ;
    int price ;
    String descriptions ;
    String starRate ;
    String type ;
    String img_Url ;

    public Recommends(){
    }

    public Recommends(String name, int price, String descriptions, String starRate, String type, String img_Url) {
        this.name = name;
        this.price = price;
        this.descriptions = descriptions;
        this.starRate = starRate;
        this.type = type;
        this.img_Url = img_Url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
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
