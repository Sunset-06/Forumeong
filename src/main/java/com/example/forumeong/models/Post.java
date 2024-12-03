package com.example.forumeong.models;

import com.google.cloud.firestore.annotation.ServerTimestamp;
import com.google.cloud.Timestamp;

public class Post {
    private String id;
    private String authorId;
    private String authorName;
    private String pfpUrl;
    @ServerTimestamp
    private Timestamp created;
    private String quoting;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String aid) {
        this.authorId = aid;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPfpUrl() {
        return pfpUrl;
    }

    public void setPfpUrl(String pfpUrl) {
        this.pfpUrl = pfpUrl;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    
    public String getQuoting() {
        return quoting;
    }

    public void setQuoting(String quoting) {
        this.quoting = quoting;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //I'm not too sure about adding likes
    /* public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    } */
}
