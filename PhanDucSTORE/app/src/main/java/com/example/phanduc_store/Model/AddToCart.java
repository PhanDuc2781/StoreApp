package com.example.phanduc_store.Model;

import java.io.Serializable;

public class AddToCart implements Serializable {
    String productName ;
    String productPrice;
    String TotalProducts ;
    String CurrentDate ;
    String CurrentTime ;
    String type ;
    String Id ;
    int TotalPrice ;

    public AddToCart(){

    }

    public AddToCart(String productName, String productPrice, String totalProducts, String currentDate, String currentTime, String type , String id, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        TotalProducts = totalProducts;
        CurrentDate = currentDate;
        CurrentTime = currentTime;
        this.type = type ;
        Id = id ;
        TotalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getTotalProducts() {
        return TotalProducts;
    }

    public void setTotalProducts(String totalProducts) {
        TotalProducts = totalProducts;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }


    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }
}
