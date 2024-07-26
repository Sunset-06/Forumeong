package com.example.forumeong.models;

import com.google.cloud.firestore.annotation.ServerTimestamp;
import com.google.cloud.Timestamp;

public class User {
    private String id;
    private String username;
    private String email;
    private String pfp;

    @ServerTimestamp
    private Timestamp created;

    private String desc;
    private int postsCount;
    private int threadsCount;

    public User(String id, String username, String email, String pfp, String desc, int postsCount, int threadsCount) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pfp = pfp;
        this.desc = desc;
        this.postsCount = postsCount;
        this.threadsCount = threadsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPfp() {
        return pfp;
    }

    public void setProfilePicture(String pfp) {
        this.pfp = pfp;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDesc() {
        return desc;
    }

    public void setBio(String desc) {
        this.desc = desc;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }
}
