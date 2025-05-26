package com.goodgiants.corpoevent;

import java.time.LocalDateTime;

public class Comment {
    User author;
    LocalDateTime date;
    String body;
    Event event;


    // Getters
    public User getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public Event getEvent() {
        return event;
    }


    // Setters
    public void setBody(String body) {
        this.body = body;
    }
}
