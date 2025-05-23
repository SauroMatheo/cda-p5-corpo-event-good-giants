package com.goodgiants.corpoevent;

public class User {
    String uuid;
    String name;
    String lastName;
    String email;
    Boolean admin;

    // Getters
    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isAdmin() {
        return admin;
    }
}
