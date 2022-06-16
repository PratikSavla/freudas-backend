package com.freudas.dev.viewmodel;

public class WorkQuestionTagViewModel {
    private long id;
    private String tag;
    public WorkQuestionTagViewModel() {

    }
    public WorkQuestionTagViewModel(long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
