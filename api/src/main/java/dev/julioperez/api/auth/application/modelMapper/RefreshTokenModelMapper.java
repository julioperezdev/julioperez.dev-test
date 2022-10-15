package dev.julioperez.api.auth.application.modelMapper;

import dev.julioperez.api.auth.domain.model.RefreshToken;
import dev.julioperez.api.auth.domain.port.mapper.RefreshTokenMapper;
import dev.julioperez.api.auth.infrastructure.repository.model.RefreshTokenEntity;

import java.util.Calendar;
import java.util.UUID;

public class RefreshTokenModelMapper implements RefreshTokenMapper {
    @Override
    public RefreshToken toRefreshToken() {
        return new RefreshToken(UUID.randomUUID().toString(), Calendar.getInstance());
    }

    @Override
    public RefreshToken toRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        return new RefreshToken(refreshTokenEntity.getId(),refreshTokenEntity.getToken(),refreshTokenEntity.getCreatedDate());
    }

    @Override
    public RefreshTokenEntity toRefreshTokenEntity(RefreshToken refreshToken) {
        return new RefreshTokenEntity(refreshToken.getToken(),refreshToken.getCreateDate());
    }
}
