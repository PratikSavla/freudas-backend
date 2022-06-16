package com.freudas.dev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class WorkUserQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double answer;
    private LocalDateTime dateTimeAdded;
    private LocalDateTime dateTimeAnswered;
    private boolean skipped;
    private String remarks;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private WorkQuestion question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer(Double answer) {
        this.answer = answer;
    }

    public LocalDateTime getDateTimeAdded() {
        return dateTimeAdded;
    }

    public void setDateTimeAdded(LocalDateTime dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }

    public LocalDateTime getDateTimeAnswered() {
        return dateTimeAnswered;
    }

    public void setDateTimeAnswered(LocalDateTime dateTimeAnswered) {
        this.dateTimeAnswered = dateTimeAnswered;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkQuestion getQuestion() {
        return question;
    }

    public void setQuestion(WorkQuestion question) {
        this.question = question;
    }
}
