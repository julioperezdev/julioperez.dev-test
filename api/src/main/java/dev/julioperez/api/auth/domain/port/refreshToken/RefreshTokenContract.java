package dev.julioperez.api.auth.domain.port.refreshToken;

import dev.julioperez.api.auth.domain.model.RefreshToken;

public interface RefreshTokenContract {
    RefreshToken generateRefreshToken();


}
