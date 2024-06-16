package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Review;
import org.example.urbanpassplatform.repository.EventRepository;
import org.example.urbanpassplatform.repository.ReviewRepository;
import org.example.urbanpassplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/insert")
    public Review insertReview(@RequestBody Review review) {
        if (!eventRepository.existsById(review.getEventId())) {
            throw new RuntimeException("Event not found");
        }
        if (!userRepository.existsById(review.getUserId())) {
            throw new RuntimeException("User not found");
        }

        return reviewRepository.save(review);
    }

    @GetMapping("/findAll")
    public List<Review> getReview() {
        return reviewRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Review getReviewById(@PathVariable String id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable String id) {
        reviewRepository.deleteById(id);
    }

    @PutMapping("/incrementLikes/{id}")
    public Review incrementLikes(@PathVariable String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.incrementLikes();
        return reviewRepository.save(review);
    }
}
