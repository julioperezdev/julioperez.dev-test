package dev.julioperez.api.auth.application.login.delivery;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginContract;
import dev.julioperez.api.auth.domain.port.login.LoginInputPort;

public class LoginDelivery implements LoginInputPort {

    private final LoginContract loginContract;

    public LoginDelivery(LoginContract loginContract) {
        this.loginContract = loginContract;
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
        return loginContract.loginUser(loginRequest);
    }
}
