package com.freudas.dev.viewmodel.bigfive;

import com.freudas.dev.model.BigFiveAnswer;
import com.freudas.dev.model.BigFiveTags;

public class BigFiveQuestionViewModel {
    private String displayQuestion;
    private BigFiveTags tag;
    private long id;

    public BigFiveQuestionViewModel(BigFiveAnswer bfa) {
        this.displayQuestion = bfa.getQuestion().getDisplayQuestion();
        this.tag = bfa.getQuestion().getTag();
        this.id = bfa.getId();
    }

    public String getDisplayQuestion() {
        return displayQuestion;
    }

    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }

    public BigFiveTags getTag() {
        return tag;
    }

    public void setTag(BigFiveTags tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
