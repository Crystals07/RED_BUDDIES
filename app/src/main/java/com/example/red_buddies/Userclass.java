package com.example.red_buddies;

public class
Userclass {
    public String name;
    public String phone;
    public String blood;
    public String email;
    public String password;
    public String city;

    public Userclass(String name, String phone, String email,String password,int x) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Userclass(String name,String phone)
    {
        this.name=name;
        this.phone=phone;
    }





    public Userclass(String d_name, String d_phone, String d_blood,String d_city)
    {
        this.name = d_name;
        this.phone = d_phone;
        this.blood = d_blood;
        this.city=d_city;
    }
}
