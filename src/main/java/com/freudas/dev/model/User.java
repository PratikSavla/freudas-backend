package com.freudas.dev.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @OneToOne(mappedBy = "user")
    private Recommendations recommendations;

    private String name;
    private String email;
    private String imageUrl;
    private String resetKey;
    private String role;
    private Boolean emailVerified = false;
    private String password;
    private String providerId;
    private boolean isAdminBlocked = false;
    private boolean isLoginBlocked = false;

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
    }

    public boolean isLoginBlocked() {
        return isLoginBlocked;
    }

    public void setLoginBlocked(boolean loginBlocked) {
        isLoginBlocked = loginBlocked;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public boolean isAdminBlocked() {
        return isAdminBlocked;
    }

    public void setAdminBlocked(boolean adminBlocked) {
        isAdminBlocked = adminBlocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
