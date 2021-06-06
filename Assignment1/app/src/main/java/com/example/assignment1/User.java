package com.example.assignment1;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final String phone;

    public User() {
        username = "";
        password = "";
        email = "";
        phone = "";
    }

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }
}