package com.freudas.dev.model;

import javax.persistence.Entity;

@Entity
public class GrowingWorkQuestion extends WorkQuestion{
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
