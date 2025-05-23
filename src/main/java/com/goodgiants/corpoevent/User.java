package com.goodgiants.corpoevent;

import java.util.ArrayList;
import java.util.List;

public class User {
    String uuid;
    String name;
    String lastName;
    String email;
    Boolean admin;

    List<Comment> comments;

    public User(String uuid, String name, String lastName, String email, Boolean admin) {
        this.uuid = uuid;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;

        this.comments = new ArrayList<>();
    }


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

    public List<Comment> getComments() {
        return comments;
    }
}
