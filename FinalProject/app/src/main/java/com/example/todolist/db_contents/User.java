package com.example.todolist.db_contents;

import java.util.HashMap;

public class User {
    private final String username;
    private String password;
//    private HashMap<Integer, Task> task_list;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
