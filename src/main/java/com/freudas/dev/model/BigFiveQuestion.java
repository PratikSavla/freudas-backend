package com.freudas.dev.model;

import javax.persistence.*;

@Entity
public class BigFiveQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String displayQuestion;
    private boolean isReverse;

    @Enumerated(EnumType.STRING)
    private BigFiveTags tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayQuestion() {
        return displayQuestion;
    }

    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public BigFiveTags getTag() {
        return tag;
    }

    public void setTag(BigFiveTags tag) {
        this.tag = tag;
    }
}
