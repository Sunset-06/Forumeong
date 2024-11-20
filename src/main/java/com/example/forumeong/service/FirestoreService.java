package com.example.forumeong.service;

import com.example.forumeong.models.User;
import com.example.forumeong.models.Thread;
import com.example.forumeong.models.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

        public String createUser(User user, String userId) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                    .collection("users")
                    .document(userId)
                    .set(user); 
            collectionsApiFuture.get();
            return userId; 
        }

    public String updateUser(String userId, User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection("users")
                .document(userId)
                .update(
                    "username", user.getUsername(),
                    "bio", user.getBio()
                );
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(userId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference users = dbFirestore.collection("users");
        ApiFuture<QuerySnapshot> future = users.get();
        List<User> userList = future.get().toObjects(User.class);
        return userList;
    }

    public String deleteUser(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> result = dbFirestore.collection("users").document(userId).delete();
        return "User with ID " + userId + " has been deleted successfully.";
    }

    //------------------------------Threads------------------------------------------------------

    public String createThread(Thread thread) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore
                .collection("threads")
                .add(thread); 
        DocumentReference documentReference = collectionsApiFuture.get();
        thread.setId(documentReference.getId()); 
        return documentReference.getId(); 
    }
    
    public String updateThread(String threadId, Thread thread) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection("threads")
                .document(threadId)
                .set(thread, SetOptions.merge());
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Thread getThreadById(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("threads").document(threadId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Thread.class);
        } else {
            return null;
        }
    }

    public List<Thread> getAllThreads() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference threads = dbFirestore.collection("threads");
        ApiFuture<QuerySnapshot> future = threads.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Thread> threadList = new ArrayList<>();
        
        for (QueryDocumentSnapshot document : documents) {
            Thread thread = document.toObject(Thread.class);
            thread.setId(document.getId()); 
            threadList.add(thread);
        }

        return threadList;
    }

    public String deleteThread(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> result = dbFirestore.collection("threads").document(threadId).delete();
        return "Thread with ID " + threadId + " has been deleted successfully.";
    }

    //----------------------------------Posts----------------------------------------------------------

    public String createPost(String threadId, Post post) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference postRef = dbFirestore
                .collection("threads")
                .document(threadId)
                .collection("posts")
                .document(); 
        post.setId(postRef.getId());
        ApiFuture<WriteResult> writeResult = postRef.set(post);
        return postRef.getId();
    }
    
    public Post getPostById(String threadId, String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference postRef = dbFirestore.collection("threads").document(threadId).collection("posts").document(postId);
        ApiFuture<DocumentSnapshot> future = postRef.get();
        DocumentSnapshot document = future.get();
    
        if (document.exists()) {
            return document.toObject(Post.class);
        } else {
            return null;
        }
    }

    public List<Post> getPostsByThreadId(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference posts = dbFirestore.collection("threads").document(threadId).collection("posts");
        ApiFuture<QuerySnapshot> future = posts.get();
        List<Post> postList = future.get().toObjects(Post.class);
        return postList;
    }

    public String deletePost(String threadId, String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference postRef = dbFirestore.collection("threads").document(threadId).collection("posts").document(postId);
        ApiFuture<WriteResult> result = postRef.delete();
        return "Post with ID " + postId + " has been deleted successfully.";
    }

    public void updateThreadPostCount(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference threadRef = dbFirestore.collection("threads").document(threadId);
        DocumentSnapshot threadSnapshot = threadRef.get().get();
    
        if (threadSnapshot.exists()) {
            Thread thread = threadSnapshot.toObject(Thread.class);
            if (thread != null) {
                int newPostCount = thread.getPostCount() + 1; 
                threadRef.update("postCount", newPostCount);
            }
        }
    }
}

