package dev.julioperez.api.auth.domain.port.login;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;

public interface LoginInputPort {

    AuthenticationResponse loginUser(LoginRequest loginRequest);
}
