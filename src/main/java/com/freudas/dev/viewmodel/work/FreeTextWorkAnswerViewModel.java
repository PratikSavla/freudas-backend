package com.freudas.dev.viewmodel.work;

public class FreeTextWorkAnswerViewModel {
    private long questionId;
    private String answer;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public double getScore() {
        return 50;
    }
}
