package org.example.urbanpassplatform.repository;

import org.example.urbanpassplatform.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
