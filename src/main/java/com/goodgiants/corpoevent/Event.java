package com.goodgiants.corpoevent;

import java.time.LocalDateTime;

public class Event {
    int id;
    String location;
    String name;
    String description;
    LocalDateTime dateTime;
    LocalDateTime endDateTime;

    public Event(int id, String location, String name, String description, LocalDateTime dateTime, LocalDateTime endDateTime) {
        if (dateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("The start of the event would happen after its end.");
        }
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
    }


    // Getters
    public int getId() {
        return this.id;
    }

    public String getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }


    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("The start of the event would happen after its end.");
        }
        this.dateTime = dateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        if (endDateTime.isBefore(dateTime)) {
            throw new IllegalArgumentException("The end of the event would happen before its beginning.");
        }
        this.endDateTime = endDateTime;
    }

}
