package com.freudas.dev.viewmodel.work;

import java.util.List;

public class BinaryWorkQuestionViewModel extends WorkQuestionTemplate {
    private String yesLabel;
    private String noLabel;
    private String notSureLabel;
    private List<String> statements;

    public String getYesLabel() {
        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    public String getNotSureLabel() {
        return notSureLabel;
    }

    public void setNotSureLabel(String notSureLabel) {
        this.notSureLabel = notSureLabel;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements) {
        this.statements = statements;
    }
}
