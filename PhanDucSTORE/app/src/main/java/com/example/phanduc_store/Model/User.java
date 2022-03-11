package com.example.phanduc_store.Model;

public class User {
    String uID ;
    String name ;
    String email ;
    String imageURL;
    public User(){

    }

    public User(String uID ,String name, String email, String imageURL) {
        this.uID = uID;
        this.name = name;
        this.email = email;
        this.imageURL = imageURL;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
