package com.example.myfirstapp;

public class User {
    String email;
    int clicks;

    public User() {}
    public User(String email) {
        this.email = email;
    }
    public User(String email, int clicks) {
        this.email = email;
        this.clicks = clicks;
    }

    public String getEmail() {
        return this.email;
    }
    public int getClicks() {
        return this.clicks;
    }
}
