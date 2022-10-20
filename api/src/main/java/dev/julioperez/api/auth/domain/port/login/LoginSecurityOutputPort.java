package dev.julioperez.api.auth.domain.port.login;

import dev.julioperez.api.auth.domain.model.LoginRequest;

public interface LoginSecurityOutputPort {

    String generateTokenByLoginRequest(LoginRequest loginRequest);
}
