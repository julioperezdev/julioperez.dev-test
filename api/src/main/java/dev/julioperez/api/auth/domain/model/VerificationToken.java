package dev.julioperez.api.auth.domain.model;

import java.util.UUID;

public class VerificationToken {

    private UUID id;
    private UUID token;
    private UUID userId;

    public VerificationToken(UUID id, UUID token, UUID userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public VerificationToken(UUID token, UUID userId) {
        this.token = token;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}

