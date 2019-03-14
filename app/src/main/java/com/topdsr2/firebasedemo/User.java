package com.topdsr2.firebasedemo;

import java.util.ArrayList;

public class User {
    public String email;
    public String name;
    public String id;
    public ArrayList<String> friend;
    public ArrayList<String> check;
    public ArrayList<String> apply;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<String> friend) {
        this.friend = friend;
    }

    public ArrayList<String> getCheck() {
        return check;
    }

    public void setCheck(ArrayList<String> check) {
        this.check = check;
    }

    public ArrayList<String> getApply() {
        return apply;
    }

    public void setApply(ArrayList<String> apply) {
        this.apply = apply;
    }
}
