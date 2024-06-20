package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.entity.Comment;
import org.example.urbanpassplatform.entity.Reaction;
import org.example.urbanpassplatform.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/insert/{reviewId}/{userId}")
    public Comment insertComment(@PathVariable String reviewId, @PathVariable String userId, @RequestBody Comment comment) {
        comment.setReaction(new Reaction());

        if (commentRepository.existsByReviewId(reviewId)) {
            throw new RuntimeException("Review already has a comment");
        }
        if (commentRepository.existsByUserId(userId)) {
            throw new RuntimeException("User already has a comment");
        }
        comment.setReviewId(reviewId);
        comment.setUserId(userId);
        return commentRepository.save(comment);
    }

    @GetMapping("/findAll")
    public List<Comment> getComment() {
        return commentRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Comment getCommentById(@PathVariable String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable String id) {
        commentRepository.deleteById(id);
    }

    @PostMapping("/react/{commentId}/{userId}")
    public Comment react(@PathVariable String commentId, @PathVariable String userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if (comment.getReaction().getUserIdList().contains(userId)) {
            throw new RuntimeException("User already reacted");
        }
        comment.getReaction().getUserIdList().add(userId);

        return commentRepository.save(comment);
    }

    @PutMapping("/unreact/{commentId}/{userId}")
    public Comment unreact(@PathVariable String commentId, @PathVariable String userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getReaction().getUserIdList().contains(userId)) {
            throw new RuntimeException("User has not reacted yet");
        }
        comment.getReaction().getUserIdList().remove(userId);

        return commentRepository.save(comment);
    }
}
