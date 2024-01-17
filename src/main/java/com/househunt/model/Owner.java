package com.househunt.model;

public class Owner {
    private int ownerId;
    private String username;
    private String password;

    // Constructors, getters, setters

    // Default constructor
    public Owner() {
    }

    // Parameterized constructor
    public Owner(int ownerId, String username, String password) {
        this.ownerId = ownerId;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
