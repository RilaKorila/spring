package com.example.samplewebfluxapp;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private int userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    public Post(){
        super();
    }

    public Post(int id, int userId, String title, String body){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String toString(){
        return ("id: " + id + ", userId: " + userId + ", title: " + title + ", body: " + body);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
