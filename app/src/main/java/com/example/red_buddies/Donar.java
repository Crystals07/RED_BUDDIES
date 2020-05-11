package com.example.red_buddies;

public class Donar {
    private String name;
    private String city;
    private String blood;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Donar() {
    }

    public Donar(String name, String city, String blood, String phone) {
        this.name = name;
        this.city = city;
        this.blood = blood;
        this.phone = phone;
    }
}

