package dev.julioperez.api.auth.application.refreshToken.adapter;

import dev.julioperez.api.auth.domain.model.RefreshToken;
import dev.julioperez.api.auth.domain.port.mapper.RefreshTokenMapper;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenOutputPort;
import dev.julioperez.api.auth.infrastructure.repository.dao.RefreshTokenDao;
import dev.julioperez.api.auth.infrastructure.repository.model.RefreshTokenEntity;

import java.util.Objects;

public class RefreshTokenAdapterRepository implements RefreshTokenOutputPort {

    private final RefreshTokenDao refreshTokenDao;
    private final RefreshTokenMapper refreshTokenMapper;

    public RefreshTokenAdapterRepository(RefreshTokenDao refreshTokenDao, RefreshTokenMapper refreshTokenMapper) {
        this.refreshTokenDao = refreshTokenDao;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    @Override
    public RefreshToken createRefreshToken(RefreshToken generatedRefreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.toRefreshTokenEntity(generatedRefreshToken);
        refreshTokenDao.saveAndFlush(refreshTokenEntity);
        if(hasRefreshTokenBeenCreated(refreshTokenEntity)) throw new RuntimeException("refreshTokenEntity has not been created in database");
        return refreshTokenMapper.toRefreshToken(refreshTokenEntity);
    }

    private boolean hasRefreshTokenBeenCreated(RefreshTokenEntity refreshTokenEntity){
        return Objects.isNull(refreshTokenEntity.getId());
    }

}
