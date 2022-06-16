package com.freudas.dev.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;

    private ArrayList<ChatMessage> messages = new ArrayList<>();
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public LocalDateTime getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(LocalDateTime endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }
}
