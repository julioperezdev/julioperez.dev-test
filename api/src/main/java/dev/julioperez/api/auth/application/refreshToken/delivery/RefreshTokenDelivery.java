package dev.julioperez.api.auth.application.refreshToken.delivery;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.RefreshTokenRequest;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenContract;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenInputPort;

public class RefreshTokenDelivery implements RefreshTokenInputPort {

    private final RefreshTokenContract refreshTokenContract;

    public RefreshTokenDelivery(RefreshTokenContract refreshTokenContract) {
        this.refreshTokenContract = refreshTokenContract;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenContract.refreshTokenByToken(refreshTokenRequest);
    }
}
