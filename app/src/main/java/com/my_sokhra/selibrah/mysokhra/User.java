package com.my_sokhra.selibrah.mysokhra;

public class User {

    String name;
    String email;
    String num;
    String location;

    public User(){

    }
    public User(String name, String email, String num, String location) {
        this.name = name;
        this.email = email;
        this.num = num;
        this.location = location;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void init() {
        this.name = "";
        this.email = "";
        this.location = "";
    }
}
