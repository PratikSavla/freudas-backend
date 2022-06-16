package com.freudas.dev.viewmodel;

import java.util.List;

public class ProviderDetailsViewModel {
    private long id;
    private String provider;
    private String companyName;
    private String domain;
    private String loginURL;
    private String ownerName;
    private String ownerEmail;
    private String ownerImageUrl;
    private List<WorkQuestionTagViewModel> preferredTags;

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerImageUrl() {
        return ownerImageUrl;
    }

    public void setOwnerImageUrl(String ownerImageUrl) {
        this.ownerImageUrl = ownerImageUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<WorkQuestionTagViewModel> getPreferredTags() {
        return preferredTags;
    }

    public void setPreferredTags(List<WorkQuestionTagViewModel> preferredTags) {
        this.preferredTags = preferredTags;
    }
}
