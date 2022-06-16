package com.freudas.dev.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime timeStamp;
    private Double score;

    @ManyToOne
    private User user;

    public UserAnalysis() {
    }

    public UserAnalysis(Double score, User user, LocalDateTime timeStamp) {
        this.score = score;
        this.user = user;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
