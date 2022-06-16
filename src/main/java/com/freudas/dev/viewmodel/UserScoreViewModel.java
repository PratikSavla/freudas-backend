package com.freudas.dev.viewmodel;

import java.time.LocalDateTime;
import java.util.List;

public class UserScoreViewModel {
    private long questionId;
    private double answer;
    private String remark;
    private String question;
    private LocalDateTime dateTimeAdded;
    private LocalDateTime dateTimeAnswered;
    private List<WorkQuestionTagViewModel> tags;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<WorkQuestionTagViewModel> getTags() {
        return tags;
    }

    public void setTags(List<WorkQuestionTagViewModel> tags) {
        this.tags = tags;
    }
}
