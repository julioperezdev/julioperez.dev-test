package dev.julioperez.api.auth.domain.port.login;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;

public interface LoginContract {
    AuthenticationResponse loginUser(LoginRequest loginRequest);
}
