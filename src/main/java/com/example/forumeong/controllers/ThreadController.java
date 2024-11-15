    package com.example.forumeong.controllers;

    import com.example.forumeong.models.Thread;
    import com.example.forumeong.service.FirestoreService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.concurrent.ExecutionException;

    @RestController
    @RequestMapping("/api/threads/")
    public class ThreadController {

        @Autowired
        private FirestoreService firestoreService;

        @PostMapping
        public ResponseEntity<String> createThread(@RequestBody Thread thread) {
            try {
                String response = firestoreService.createThread(thread);
                return ResponseEntity.status(HttpStatus.CREATED).body("Thread created at: " + response);
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<String> updateThread(@PathVariable String id, @RequestBody Thread thread) {
            try {
                String response = firestoreService.updateThread(id, thread);
                return ResponseEntity.ok("Thread updated at: " + response);
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Thread> getThreadById(@PathVariable String id) {
            try {
                Thread thread = firestoreService.getThreadById(id);
                if (thread != null) {
                    return ResponseEntity.ok(thread);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        @GetMapping
        public ResponseEntity<List<Thread>> getAllThreads() {
            try {
                List<Thread> threads = firestoreService.getAllThreads();
                return ResponseEntity.ok(threads);
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteThread(@PathVariable String id) {
            try {
                String response = firestoreService.deleteThread(id);
                return ResponseEntity.ok(response);
            } catch (ExecutionException | InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    }
