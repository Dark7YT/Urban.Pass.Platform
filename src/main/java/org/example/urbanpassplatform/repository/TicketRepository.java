package org.example.urbanpassplatform.repository;

import org.example.urbanpassplatform.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
