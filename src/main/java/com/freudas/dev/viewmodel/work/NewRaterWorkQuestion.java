package com.freudas.dev.viewmodel.work;

import java.util.List;

public class NewRaterWorkQuestion {
    private int id;
    private String displayQuestion;
    private List<Integer> tags;
    private int noOfChoices;
    private boolean allowOwnWords;
    private List<String> predefinedWords;

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

    public int getNoOfChoices() {
        return noOfChoices;
    }

    public void setNoOfChoices(int noOfChoices) {
        this.noOfChoices = noOfChoices;
    }

    public boolean isAllowOwnWords() {
        return allowOwnWords;
    }

    public void setAllowOwnWords(boolean allowOwnWords) {
        this.allowOwnWords = allowOwnWords;
    }

    public List<String> getPredefinedWords() {
        return predefinedWords;
    }

    public void setPredefinedWords(List<String> predefinedWords) {
        this.predefinedWords = predefinedWords;
    }
}
