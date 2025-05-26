package com.goodgiants.corpoevent;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorpoeventApplication {
	public static void main(String[] args) {
		EventHandler handler = new EventHandler();

		Event event = new Event(
				104,
				"Paris 3.0",
				"Good Giants Meetup",
				"Networking event for Good Giants",
				LocalDateTime.of(2025, 6, 10, 18, 0),
				LocalDateTime.of(2025, 6, 10, 21, 0)
		);

		//handler.addEvent(event);
		//handler.updateEvent(event);
		handler.deleteEvent(102);
	}
}