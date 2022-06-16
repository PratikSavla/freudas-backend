package com.freudas.dev.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email","username"}) })
public class WorkProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String companyName;
    private String domain;
    private String loginURL;
    private boolean isAdminBlocked = false;

    @OneToOne
    private User owner;

    @OneToMany
    private List<WorkQuestionTag> preferedTags = new ArrayList<>();

    public List<WorkQuestionTag> getPreferedTags() {
        return preferedTags;
    }

    public void setPreferedTags(List<WorkQuestionTag> preferedTags) {
        this.preferedTags = preferedTags;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isAdminBlocked() {
        return isAdminBlocked;
    }

    public void setAdminBlocked(boolean adminBlocked) {
        isAdminBlocked = adminBlocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
