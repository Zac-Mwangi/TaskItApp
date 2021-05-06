package com.example.taskit.Extras;

public class SampleModel {
    private String fullname, email, phone, location_string;

    public SampleModel(String fullname, String email, String phone, String location_string) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.location_string = location_string;
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
}