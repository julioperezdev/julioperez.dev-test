package dev.julioperez.api.auth.domain.port.refreshToken;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.RefreshTokenRequest;

public interface RefreshTokenInputPort {

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
