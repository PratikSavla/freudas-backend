package com.freudas.dev.viewmodel.work;

import java.util.List;

public class SliderWorkAnswerViewModel {
    private long questionId;
    private List<Integer> answers;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
    public double getScore() {
        double sum = 0;
        for(int integer: this.answers) {
            sum+=integer;
        };
        return this.answers.size() > 0 ? sum / this.answers.size() : 0;
    }
}
