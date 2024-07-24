package com.example.forumeong.service;

import com.example.forumeong.models.User;
import com.example.forumeong.models.Thread;
import com.example.forumeong.models.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private static final String Users = "Users";
    private static final String Threads = "Threads";
    private static final String Posts= "Posts";

    // CRUD operations for User

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(Users)
                .document(user.getId())
                .set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(Users).document(userId);
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
        CollectionReference users = dbFirestore.collection(Users);
        ApiFuture<QuerySnapshot> future = users.get();
        List<User> userList = future.get().toObjects(User.class);
        return userList;
    }

    public String deleteUser(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(Users).document(userId).delete();
        return "User with ID " + userId + " has been deleted successfully.";
    }

    // CRUD operations for Thread

    public String saveThread(Thread thread) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(Threads)
                .document(thread.getId())
                .set(thread);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Thread getThreadById(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(Threads).document(threadId);
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
        CollectionReference threads = dbFirestore.collection(Threads);
        ApiFuture<QuerySnapshot> future = threads.get();
        List<Thread> threadList = future.get().toObjects(Thread.class);
        return threadList;
    }

    public String deleteThread(String threadId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(Threads).document(threadId).delete();
        return "Thread with ID " + threadId + " has been deleted successfully.";
    }

    // CRUD operations for Post

    public String savePost(Post post) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(Posts)
                .document(post.getId())
                .set(post);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Post getPostById(String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(Posts).document(postId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Post.class);
        } else {
            return null;
        }
    }

    public List<Post> getAllPosts() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference posts = dbFirestore.collection(Posts);
        ApiFuture<QuerySnapshot> future = posts.get();
        List<Post> postList = future.get().toObjects(Post.class);
        return postList;
    }

    public String deletePost(String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(Posts).document(postId).delete();
        return "Post with ID " + postId + " has been deleted successfully.";
    }
}

