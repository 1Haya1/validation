package org.example.event.EventController;


import jakarta.validation.Valid;
import org.example.event.Api.ApiResponse;
import org.example.event.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final List<Event> events = new ArrayList<>();
    private int nextId = 1;

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event) {
        event.setId(nextId++);
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("event added"));
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return events;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @Valid @RequestBody Event updatedEvent) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setDescription(updatedEvent.getDescription());
                event.setCapacity(updatedEvent.getCapacity());
                event.setStartDate(updatedEvent.getStartDate());
                event.setEndDate(updatedEvent.getEndDate());
                return ResponseEntity.status(200).body(new ApiResponse("event update"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("event not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id) {
        if (events.removeIf(event -> event.getId() == id)) {
            return ResponseEntity.status(200).body(new ApiResponse("event with Id " + id + " deleted"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("event not found"));
        }
    }


    @GetMapping("/search/{id}")
    public ResponseEntity searchEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
}



