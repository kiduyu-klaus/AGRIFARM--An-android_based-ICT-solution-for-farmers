package com.kiduyu.njugunaproject.agrifarm.Model;

public class Application {
    String consultant_name, username, userphone, consultant_phone, dateValue, time;

    public Application(String consultant_name, String username, String userphone, String consultant_phone, String dateValue, String time) {
        this.consultant_name = consultant_name;
        this.username = username;
        this.userphone = userphone;
        this.consultant_phone = consultant_phone;
        this.dateValue = dateValue;
        this.time = time;
    }

    public String getConsultant_name() {
        return consultant_name;
    }

    public void setConsultant_name(String consultant_name) {
        this.consultant_name = consultant_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getConsultant_phone() {
        return consultant_phone;
    }

    public void setConsultant_phone(String consultant_phone) {
        this.consultant_phone = consultant_phone;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
