package dev.julioperez.api.auth.domain.port.refreshToken;

import dev.julioperez.api.auth.domain.model.RefreshToken;

public interface RefreshTokenOutputPort {
    RefreshToken createRefreshToken(RefreshToken generatedRefreshToken);
}
