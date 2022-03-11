package com.example.phanduc_store.Model;

public class PopularProducts {
    String name ;
    String descriptions ;
    String starRate ;
    String discount ;
    String type ;
    String img_Url ;

    public PopularProducts(){

    }

    public PopularProducts( String name, String descriptions, String starRate,
                           String discount, String type, String img_Url) {
        this.name = name;
        this.descriptions = descriptions;
        this.starRate = starRate;
        this.discount = discount;
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

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
