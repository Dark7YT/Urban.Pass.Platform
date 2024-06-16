package org.example.urbanpassplatform.repository;

import org.example.urbanpassplatform.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
    boolean existsByReviewId(String reviewId);
    boolean existsByUserId(String userId);}
