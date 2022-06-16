package com.freudas.dev.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "dilemmaId")
public class DilemmaWorkQuestion extends  WorkQuestion{
    private String minLabel;
    private String maxLabel;
    private String minEmoji;
    private String maxEmoji;

    public String getMinLabel() {
        return minLabel;
    }

    public void setMinLabel(String minLabel) {
        this.minLabel = minLabel;
    }

    public String getMaxLabel() {
        return maxLabel;
    }

    public void setMaxLabel(String maxLabel) {
        this.maxLabel = maxLabel;
    }

    public String getMinEmoji() {
        return minEmoji;
    }

    public void setMinEmoji(String minEmoji) {
        this.minEmoji = minEmoji;
    }

    public String getMaxEmoji() {
        return maxEmoji;
    }

    public void setMaxEmoji(String maxEmoji) {
        this.maxEmoji = maxEmoji;
    }
}
