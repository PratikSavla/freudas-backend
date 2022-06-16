package com.freudas.dev.viewmodel.work;

import java.util.List;

public class NewFreeTextWorkQuestion {
    private int id;
    private String displayQuestion;
    private List<Integer> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayQuestion() {
        return displayQuestion;
    }

    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
