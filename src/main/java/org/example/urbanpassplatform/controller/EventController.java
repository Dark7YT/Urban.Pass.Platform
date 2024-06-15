package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Event;
import org.example.urbanpassplatform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/insert")
    public Event insertEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @GetMapping("/findAll")
    public List<Event> getEvent() {
        return eventRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Event getEventById(@PathVariable String id) {
        return eventRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventRepository.deleteById(id);
    }
}
