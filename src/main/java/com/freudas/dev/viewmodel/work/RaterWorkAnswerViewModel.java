package com.freudas.dev.viewmodel.work;

import java.util.List;

public class RaterWorkAnswerViewModel {
    private long questionId;
    private List<String> answers;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    public double getScore() {
        return 50;
    }
}
