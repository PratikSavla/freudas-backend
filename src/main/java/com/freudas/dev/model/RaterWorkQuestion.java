package com.freudas.dev.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "raterId")
public class RaterWorkQuestion extends WorkQuestion{
    private int noOfChoices;
    private boolean allowOwnWords;
    private String predefinedWords;
    public List<String> getPredefinedWords() {
        return List.of(this.predefinedWords.split("\\|"));
    }
    public void addPredefinedWord(String predefinedWord) {
        this.predefinedWords = this.predefinedWords == null? predefinedWord : this.predefinedWords+"|"+predefinedWord;
    }

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

    public void setPredefinedWords(String predefinedWords) {
        this.predefinedWords = predefinedWords;
    }
}
