package com.freudas.dev.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "binaryId")
public class BinaryWorkQuestion extends WorkQuestion{
    private String yesLabel;
    private String noLabel;
    private String notSureLabel;
    private String statements;

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

    public String getStatements() {
        return statements;
    }

    public void setStatements(String statements) {
        this.statements = statements;
    }
    public List<String> getListStatements() {
        return List.of(this.statements.split("\\|"));
    }
    public void addStatement(String statement) {
        this.statements = this.statements == null?statement: this.statements+"|"+statement;
    }
}
