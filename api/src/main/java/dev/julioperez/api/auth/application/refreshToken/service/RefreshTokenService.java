package dev.julioperez.api.auth.application.refreshToken.service;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.RefreshToken;
import dev.julioperez.api.auth.domain.model.RefreshTokenRequest;
import dev.julioperez.api.auth.domain.port.mapper.AuthenticationResponseMapper;
import dev.julioperez.api.auth.domain.port.mapper.RefreshTokenMapper;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenContract;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenOutputPort;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenSecurityOutputPort;
import dev.julioperez.api.auth.domain.port.validateToken.ValidateTokenContract;

import java.util.Calendar;

public class RefreshTokenService implements RefreshTokenContract {

    private final RefreshTokenMapper refreshTokenMapper;
    private final RefreshTokenOutputPort refreshTokenOutputPort;
    private final RefreshTokenSecurityOutputPort refreshTokenSecurityOutputPort;
    private final AuthenticationResponseMapper authenticationResponseMapper;
    private final ValidateTokenContract validateToken;

    public RefreshTokenService(RefreshTokenMapper refreshTokenMapper, RefreshTokenOutputPort refreshTokenOutputPort,RefreshTokenSecurityOutputPort refreshTokenSecurityOutputPort, AuthenticationResponseMapper authenticationResponseMapper,ValidateTokenContract validateToken) {
        this.refreshTokenMapper = refreshTokenMapper;
        this.refreshTokenOutputPort = refreshTokenOutputPort;
        this.refreshTokenSecurityOutputPort = refreshTokenSecurityOutputPort;
        this.authenticationResponseMapper = authenticationResponseMapper;
        this.validateToken=validateToken;
    }

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken generatedRefreshToken = refreshTokenMapper.toRefreshToken();
        return refreshTokenOutputPort.createRefreshToken(generatedRefreshToken);
    }
    @Override
    public AuthenticationResponse refreshTokenByToken(RefreshTokenRequest refreshTokenRequest){
        //validateToken.isNotValidTokenByToken(refreshTokenRequest.refreshToken());
        String newTokenWithRefresh = refreshTokenSecurityOutputPort.generateTokeUsingEmail(refreshTokenRequest.email());
        String newRefreshToken = generateRefreshToken().getToken();
        Calendar refreshTokenDateExpiration = refreshTokenSecurityOutputPort.getCalendarWithDateOfExpiration();
        return authenticationResponseMapper.toAuthenticationResponse(newTokenWithRefresh);
    }
    private void validateRefreshTokenByUUID(String tokenToValidate){
        //try to verify that this token exist, is to same user and does have expiration
        //should be call to repository to validate if exist
    }
}
