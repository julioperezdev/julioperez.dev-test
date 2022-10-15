package dev.julioperez.api.auth.domain.port.mapper;

import dev.julioperez.api.auth.domain.model.RefreshToken;
import dev.julioperez.api.auth.infrastructure.repository.model.RefreshTokenEntity;

public interface RefreshTokenMapper {
    RefreshToken toRefreshToken();

    RefreshToken toRefreshToken(RefreshTokenEntity refreshTokenEntity);

    RefreshTokenEntity toRefreshTokenEntity(RefreshToken refreshToken);
}
