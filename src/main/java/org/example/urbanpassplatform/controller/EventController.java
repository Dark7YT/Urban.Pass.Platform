package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Event;
import org.example.urbanpassplatform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/insert")
    public Event insertEvent(@RequestPart("event") Event event, @RequestPart("image") MultipartFile image) throws IOException {
        // Convertir MultipartFile a byte[]
        byte[] imageBytes = image.getBytes();

        // Establecer la imagen en el evento
        event.setImage(imageBytes);

        // Guardar el evento en la base de datos
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
