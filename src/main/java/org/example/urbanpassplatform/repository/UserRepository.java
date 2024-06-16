package org.example.urbanpassplatform.repository;

import org.example.urbanpassplatform.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
}
