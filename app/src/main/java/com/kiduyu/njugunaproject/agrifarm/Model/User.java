package com.kiduyu.njugunaproject.agrifarm.Model;

/**
 * Created by Kiduyu klaus
 * on 11/10/2020 13:23 2020
 */
public class User {
    String fullname,phone,username,pass,image;

    public User() {}
    public User(String fullname, String phone, String username, String pass, String image) {
        this.fullname = fullname;
        this.phone = phone;
        this.username = username;
        this.pass = pass;
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
