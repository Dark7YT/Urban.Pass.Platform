package org.example.urbanpassplatform;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongoAuditing
public class UrbanPassPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrbanPassPlatformApplication.class, args);
    }
}