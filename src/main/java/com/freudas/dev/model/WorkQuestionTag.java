package com.freudas.dev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "WQuestionTag")
public class WorkQuestionTag {
    @Id
    private long id;
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<WorkQuestion> questions;

    public Set<WorkQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<WorkQuestion> questions) {
        this.questions = questions;
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