package com.freudas.dev.viewmodel.work;

import java.util.List;

public class NewMapWorkQuestion {
    private int id;
    private String displayQuestion;
    private List<Integer> tags;
    private String minHorizontalLabel;
    private String maxHorizontalLabel;
    private String minVerticalLabel;
    private String maxVerticalLabel;
    private String minHorizontalEmoji;
    private String maxHorizontalEmoji;
    private String minVerticalEmoji;
    private String maxVerticalEmoji;

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

    public String getMinHorizontalLabel() {
        return minHorizontalLabel;
    }

    public void setMinHorizontalLabel(String minHorizontalLabel) {
        this.minHorizontalLabel = minHorizontalLabel;
    }

    public String getMaxHorizontalLabel() {
        return maxHorizontalLabel;
    }

    public void setMaxHorizontalLabel(String maxHorizontalLabel) {
        this.maxHorizontalLabel = maxHorizontalLabel;
    }

    public String getMinVerticalLabel() {
        return minVerticalLabel;
    }

    public void setMinVerticalLabel(String minVerticalLabel) {
        this.minVerticalLabel = minVerticalLabel;
    }

    public String getMaxVerticalLabel() {
        return maxVerticalLabel;
    }

    public void setMaxVerticalLabel(String maxVerticalLabel) {
        this.maxVerticalLabel = maxVerticalLabel;
    }

    public String getMinHorizontalEmoji() {
        return minHorizontalEmoji;
    }

    public void setMinHorizontalEmoji(String minHorizontalEmoji) {
        this.minHorizontalEmoji = minHorizontalEmoji;
    }

    public String getMaxHorizontalEmoji() {
        return maxHorizontalEmoji;
    }

    public void setMaxHorizontalEmoji(String maxHorizontalEmoji) {
        this.maxHorizontalEmoji = maxHorizontalEmoji;
    }

    public String getMinVerticalEmoji() {
        return minVerticalEmoji;
    }

    public void setMinVerticalEmoji(String minVerticalEmoji) {
        this.minVerticalEmoji = minVerticalEmoji;
    }

    public String getMaxVerticalEmoji() {
        return maxVerticalEmoji;
    }

    public void setMaxVerticalEmoji(String maxVerticalEmoji) {
        this.maxVerticalEmoji = maxVerticalEmoji;
    }
}
