package com.example.forumeong.controllers;

import com.example.forumeong.models.Post;
import com.example.forumeong.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/threads/{threadId}/posts")
public class PostController {

    @Autowired
    private FirestoreService firestoreService;

    @PostMapping
    public ResponseEntity<String> createPost(@PathVariable String threadId, @RequestBody Post post) {
        try {
            post.setId(firestoreService.createPost(threadId, post)); 
            firestoreService.updateThreadPostCount(threadId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post created with ID: " + post.getId());
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String threadId, @PathVariable String id) {
        try {
            Post post = firestoreService.getPostById(threadId, id);
            if (post != null) {
                return ResponseEntity.ok(post);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPostsByThread(@PathVariable String threadId) {
        try {
            List<Post> posts = firestoreService.getPostsByThreadId(threadId);
            return ResponseEntity.ok(posts);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String threadId, @PathVariable String id) {
        try {
            String response = firestoreService.deletePost(threadId, id);
            firestoreService.updateThreadPostCount(threadId);
            return ResponseEntity.ok(response);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
