package com.freudas.dev.viewmodel.work;

import java.util.List;

public class RaterWorkQuestionViewModel extends WorkQuestionTemplate{
    private int noOfChoices;
    private boolean allowOwnWords;
    private List<String> predefinedWords;

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
