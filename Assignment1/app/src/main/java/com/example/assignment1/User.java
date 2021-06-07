/**
 * This contains all of a user's information derived from the sign up page upon successful registration.
 *
 * @file User.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/07/2021
 * @version 1.0
 */

package com.example.assignment1;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final String phone;

    /**
     * Default constructor
     */
    public User() {
        username = "";
        password = "";
        email = "";
        phone = "";
    }

    /**
     * Overloaded constructor to assign user information variables.
     * @param username String
     * @param password String
     * @param email String
     * @param phone String
     */
    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Return username
     * @return String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Return password
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Return email
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Return phone
     * @return String
     */
    public String getPhone() {
        return this.phone;
    }
}
