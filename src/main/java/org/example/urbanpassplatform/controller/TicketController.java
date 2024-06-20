package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Event;
import org.example.urbanpassplatform.entity.Ticket;
import org.example.urbanpassplatform.entity.User;
import org.example.urbanpassplatform.repository.EventRepository;
import org.example.urbanpassplatform.repository.TicketRepository;
import org.example.urbanpassplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sell/{eventId}/{userId}")
    public Ticket sellTicket(@PathVariable String eventId, @PathVariable String userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (event.getSoldTickets() >= event.getMaxTickets()) {
            throw new RuntimeException("No more tickets available for this event");
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setStatus("Sold");

        event.setSoldTickets(event.getSoldTickets() + 1);
        eventRepository.save(event);

        user.getTickets().add(ticket);
        userRepository.save(user);

        return ticketRepository.save(ticket);
    }
}