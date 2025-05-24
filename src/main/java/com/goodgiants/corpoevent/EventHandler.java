package com.goodgiants.corpoevent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private final File externalFile = new File("data/Events.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Event> events;

    public EventHandler() {
        mapper.registerModule(new JavaTimeModule());
        loadEvents();
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events); // Defensive copy
    }

    private void loadEvents() {
        try {
            if (externalFile.exists()) {
                // Load from external file if it exists
                events = mapper.readValue(externalFile, new TypeReference<>() {});
            } else {
                // Load initial data from classpath resource
                try (InputStream is = getClass().getClassLoader().getResourceAsStream("Events.json")) {
                    if (is != null) {
                        events = mapper.readValue(is, new TypeReference<>() {});
                        // Ensure data directory exists
                        externalFile.getParentFile().mkdirs();
                        // Save initial data to external file for future persistence
                        saveEvents();
                    } else {
                        // No resource found, start empty
                        events = new ArrayList<>();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            events = new ArrayList<>();
        }
    }

    private void saveEvents() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(externalFile, events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(Event event) {
        try {
            // Find max id
            int maxId = events.stream()
                    .mapToInt(Event::getId)
                    .max()
                    .orElse(0); // If empty list, start from 0

            // Create a new Event with a new ID but other fields from `event`
            Event eventWithId = new Event(
                    maxId + 1,
                    event.getLocation(),
                    event.getName(),
                    event.getDescription(),
                    event.getDateTime(),
                    event.getEndDateTime()
            );

            events.add(eventWithId);
            saveEvents();
            System.out.println("addEvent test passed!");
        } catch (Exception e) {
            System.out.println("addEvent test failed.");
            e.printStackTrace();
        }
    }



    public void updateEvent(Event event) {
        boolean found = false;

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == event.getId()) {
                events.set(i, event);
                found = true;
                break;
            }
        }

        if (!found) {
            events.add(event);
        }

        saveEvents();
    }


    public void deleteEvent(int id) {
        boolean removed = events.removeIf(e -> e.getId() == id);
        if (removed) saveEvents();
    }

    public void deleteEvent(Event event) {
        deleteEvent(event.getId());
    }

    public Event findEventById(int id) {
        return events.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
