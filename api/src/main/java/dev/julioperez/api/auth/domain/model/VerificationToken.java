package dev.julioperez.api.auth.domain.model;

import java.util.Calendar;
import java.util.UUID;

public class VerificationToken {

    private UUID id;
    private String token;
    private UUID userId;
    private Calendar expiryDate;

    public VerificationToken(UUID id, String token, UUID userId, Calendar expiryDate) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public VerificationToken(String token, UUID userId, Calendar expiryDate) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public VerificationToken(String token, UUID userId) {
        this.token = token;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }
}

