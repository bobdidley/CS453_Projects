/**
 * User data class.
 *
 * @file User.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist.db_contents;

public class User {
    private final String username;
    private String password;

    /**
     * Overload Constructor
     * @param username String
     * @param password String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

}
