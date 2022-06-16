package com.freudas.dev.model;

import javax.persistence.*;

@Entity
public class Recommendations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;

    public Recommendations() {
    }
    public Recommendations(User user) {
        this.user = user;
    }
    private String quote = "https://www.thetrendspotter.net/wp-content/uploads/2022/02/Inspirational-Quotes-About-Life.jpg";
    private String articleTitle = "How to change your habits...";
    private String articleURL = "https://health.gov/myhealthfinder/topics/doctor-visits/screening-tests/talk-your-doctor-about-depression";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleURL() {
        return articleURL;
    }

    public void setArticleURL(String articleURL) {
        this.articleURL = articleURL;
    }
}
