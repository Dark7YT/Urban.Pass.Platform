package org.example.urbanpassplatform.repository;

import org.example.urbanpassplatform.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
