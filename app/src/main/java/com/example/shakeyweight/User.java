package com.example.shakeyweight;

public class User {

    public String username;
    public String email;
    public int shakes;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, int shakes) {
        this.username = username;

        this.shakes = shakes;
    }

}