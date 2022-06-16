package com.freudas.dev.viewmodel.work;

import com.freudas.dev.viewmodel.WorkQuestionTagViewModel;

import java.util.ArrayList;
import java.util.List;
public abstract class WorkQuestionTemplate {
    private long id;
    private String displayQuestion;
    private String type;
    private List<WorkQuestionTagViewModel> tags = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayQuestion() {
        return displayQuestion;
    }

    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }

    public List<WorkQuestionTagViewModel> getTags() {
        return tags;
    }

    public void setTags(List<WorkQuestionTagViewModel> tags) {
        this.tags = tags;
    }
}
