package dev.julioperez.api.auth.domain.model;

public record RefreshTokenRequest(
        String refreshToken,
        String email
) {
}
