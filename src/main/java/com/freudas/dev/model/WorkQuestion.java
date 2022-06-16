package com.freudas.dev.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class WorkQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String displayQuestion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "work_question_tag",
            joinColumns = @JoinColumn(name = "work_question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<WorkQuestionTag> tags;

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

    public Set<WorkQuestionTag> getTags() {
        return tags;
    }

    public void setTags(Set<WorkQuestionTag> tags) {
        this.tags = tags;
    }
}
