package dev.julioperez.api.auth.domain.model;

public class VerificationTokenRequest {
    private String token;
    private String message;

    public VerificationTokenRequest(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
