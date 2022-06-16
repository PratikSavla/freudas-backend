package com.freudas.dev.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BigFiveAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // answer between 1 - 5
    @Column(precision=0, scale=5)
    private Integer answer;
    private LocalDateTime dateTimeAdded;
    private LocalDateTime dateTimeAnswered;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private BigFiveQuestion question;

    public BigFiveAnswer() {
    }

    public BigFiveAnswer(User user, BigFiveQuestion question) {
        this.dateTimeAdded = LocalDateTime.now();
        this.user = user;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigFiveQuestion getQuestion() {
        return question;
    }

    public void setQuestion(BigFiveQuestion question) {
        this.question = question;
    }

    public Integer getScore() {
        return question.isReverse()?6-answer:answer;
    }
}