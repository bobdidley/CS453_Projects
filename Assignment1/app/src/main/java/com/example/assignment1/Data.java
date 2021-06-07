package com.example.assignment1;


import android.text.TextUtils;
import android.widget.EditText;

import java.util.HashMap;

public class Data extends android.app.Application {
    public static HashMap<String, User> users;

    /**
     * Default constructor
     */
    public Data() { users = new HashMap<String , User>(); }

    /**
     * Register a new user's credentials given the username is unique. And does not register user if the username is not
     * unique. Returns true or false to indicate status of registration completion.
     * @param username String
     * @param password String
     * @param email String
     * @param phone String
     * @return boolean
     */
    public boolean register(String username, String password, String email, String phone) {
        if(!users.containsKey(username)) {
            users.put(username, new User(username, password, email, phone));
            return true;
        }
        return false;
    }

    /**
     * Checks if the login credentials are registered.
     * @param username String
     * @param password String
     * @return boolean
     */
    public boolean isRegistered(String username, String password) {
        for (String name : users.keySet()) {
            if(name.equals(username) && users.get(name).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if hashmap is empty or not.
     * @return boolean
     */
    public boolean isEmpty() {
        return users.isEmpty();
    }

    /**
     * Checks if input is empty or not.
     * @param text - string text value
     * @return empty string
     */
    public boolean isEmpty(EditText text) {

        CharSequence string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }
}
