package dev.julioperez.api.auth.domain.model;

import java.util.Calendar;
import java.util.UUID;

public class RefreshToken {
    private UUID id;
    private String token;
    private Calendar createDate;

    public RefreshToken(UUID id, String token, Calendar createDate) {
        this.id = id;
        this.token = token;
        this.createDate = createDate;
    }

    public RefreshToken(String token, Calendar createDate) {
        this.token = token;
        this.createDate = createDate;
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

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }
}

