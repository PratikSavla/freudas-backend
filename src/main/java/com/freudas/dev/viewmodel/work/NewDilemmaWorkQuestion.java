package com.freudas.dev.viewmodel.work;

import java.util.List;

public class NewDilemmaWorkQuestion {
    private int id;
    private String displayQuestion;
    private List<Integer> tags;
    private String minLabel;
    private String maxLabel;
    private String minEmoji;
    private String maxEmoji;

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

    public String getMinLabel() {
        return minLabel;
    }

    public void setMinLabel(String minLabel) {
        this.minLabel = minLabel;
    }

    public String getMaxLabel() {
        return maxLabel;
    }

    public void setMaxLabel(String maxLabel) {
        this.maxLabel = maxLabel;
    }

    public String getMinEmoji() {
        return minEmoji;
    }

    public void setMinEmoji(String minEmoji) {
        this.minEmoji = minEmoji;
    }

    public String getMaxEmoji() {
        return maxEmoji;
    }

    public void setMaxEmoji(String maxEmoji) {
        this.maxEmoji = maxEmoji;
    }
}
