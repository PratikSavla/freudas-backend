package com.freudas.dev.viewmodel.work;

import java.util.List;

public class NewBinaryWorkQuestion {
    private int id;
    private String displayQuestion;
    private String yesLabel;
    private String noLabel;
    private String notSureLabel;
    private List<String> statements;
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

    public String getYesLabel() {
        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    public String getNotSureLabel() {
        return notSureLabel;
    }

    public void setNotSureLabel(String notSureLabel) {
        this.notSureLabel = notSureLabel;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements) {
        this.statements = statements;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
