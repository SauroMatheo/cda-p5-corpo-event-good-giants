package com.goodgiants.corpoevent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private final File externalFile = new File("data/Events.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Event> events;

    public EventHandler() {
        loadEvents();
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
            // Save to external writable file
            mapper.writerWithDefaultPrettyPrinter().writeValue(externalFile, events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(Event event) {
        events.add(event);
        saveEvents();
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
