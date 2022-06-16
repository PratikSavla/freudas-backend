package com.freudas.dev.viewmodel.work;

public class MapWorkAnswerViewModel {
    private long questionId;
    private int verticalAnswer;
    private int horizontalAnswer;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getVerticalAnswer() {
        return verticalAnswer;
    }

    public void setVerticalAnswer(int verticalAnswer) {
        this.verticalAnswer = verticalAnswer;
    }

    public int getHorizontalAnswer() {
        return horizontalAnswer;
    }

    public void setHorizontalAnswer(int horizontalAnswer) {
        this.horizontalAnswer = horizontalAnswer;
    }
    public double getScore() {
        return (this.horizontalAnswer + this.verticalAnswer ) / 2;
    }
}
