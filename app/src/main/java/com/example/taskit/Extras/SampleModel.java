package com.example.taskit.Extras;

public class SampleModel {
    private String fullname, email, phone, location_string;
    int user_id;

    public SampleModel(String fullname, String email, String phone, String location_string, int user_id) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.location_string = location_string;
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation_string() {
        return location_string;
    }

    public int getUser_id() {
        return user_id;
    }
}