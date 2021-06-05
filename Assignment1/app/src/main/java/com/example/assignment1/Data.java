package com.example.assignment1;

import java.util.ArrayList;
import java.util.HashSet;

public class Data {
    private HashSet<User> users;

    public Data() {}

    public void register(String username, String password, String email, String phone) {
        users.add(new User(username, password, email, phone));
    }

    public boolean isRegistered(String username, String password) {
        for (User u : users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {

            }
        }
    }
}
