package dev.julioperez.api.emailNotifier.domain.model;

import java.util.UUID;

public class EmailRequest {
    private String email;
    private UUID token;

    public EmailRequest(String email, UUID token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
