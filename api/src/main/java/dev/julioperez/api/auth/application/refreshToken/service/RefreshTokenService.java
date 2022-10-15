package dev.julioperez.api.auth.application.refreshToken.service;

import dev.julioperez.api.auth.domain.model.RefreshToken;
import dev.julioperez.api.auth.domain.port.mapper.RefreshTokenMapper;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenContract;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenOutputPort;

public class RefreshTokenService implements RefreshTokenContract {

    private final RefreshTokenMapper refreshTokenMapper;
    private final RefreshTokenOutputPort refreshTokenOutputPort;

    public RefreshTokenService(RefreshTokenMapper refreshTokenMapper, RefreshTokenOutputPort refreshTokenOutputPort) {
        this.refreshTokenMapper = refreshTokenMapper;
        this.refreshTokenOutputPort = refreshTokenOutputPort;
    }

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken generatedRefreshToken = refreshTokenMapper.toRefreshToken();
        return refreshTokenOutputPort.createRefreshToken(generatedRefreshToken);
    }
}
