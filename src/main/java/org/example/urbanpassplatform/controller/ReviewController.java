package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Reaction;
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

    @PostMapping("/insert/{eventId}/{userId}")
    public Review insertReview(@PathVariable String eventId, @PathVariable String userId, @RequestBody Review review) {
        review.setReaction(new Reaction());

        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        review.setEventId(eventId);
        review.setUserId(userId);
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

    @PostMapping("/react/{reviewId}/{userId}")
    public Review react(@PathVariable String reviewId, @PathVariable String userId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        if (review.getReaction().getUserIdList().contains(userId)) {
            throw new RuntimeException("User has already reacted");
        }
        review.getReaction().getUserIdList().add(userId);

        return reviewRepository.save(review);
    }

    @PutMapping("/unreact/{reviewId}/{userId}")
    public Review unReact(@PathVariable String reviewId, @PathVariable String userId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getReaction().getUserIdList().contains(userId)) {
            throw new RuntimeException("User has not reacted");
        }
        review.getReaction().getUserIdList().remove(userId);

        return reviewRepository.save(review);
    }
}
