package com.example.booklify.model;

public class UserModel {

    public String username;
    public String email;
    public String image;

    public UserModel(){};

    public UserModel(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserModel(String username, String email, String image) {
        this.username = username;
        this.email = email;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
