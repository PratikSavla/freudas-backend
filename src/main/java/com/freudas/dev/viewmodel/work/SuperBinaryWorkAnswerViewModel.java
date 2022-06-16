package com.freudas.dev.viewmodel.work;

public class SuperBinaryWorkAnswerViewModel {
    private long questionId;
    private boolean answer;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    public double getScore() {
        return this.answer ? 100 : 0;
    }
}
