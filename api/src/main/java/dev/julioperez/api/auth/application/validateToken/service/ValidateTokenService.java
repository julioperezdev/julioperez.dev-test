package dev.julioperez.api.auth.application.validateToken.service;

import dev.julioperez.api.auth.domain.port.validateToken.ValidateTokenContract;
import dev.julioperez.api.auth.domain.port.validateToken.ValidateTokenSecurityOutputPort;

public class ValidateTokenService implements ValidateTokenContract {

    private final ValidateTokenSecurityOutputPort validateTokenSecurityOutputPort;

    public ValidateTokenService(ValidateTokenSecurityOutputPort validateTokenSecurityOutputPort) {
        this.validateTokenSecurityOutputPort = validateTokenSecurityOutputPort;
    }


    @Override
    public boolean isNotValidTokenByToken(String tokenToValidate) {
        return validateTokenSecurityOutputPort.isNotValidTokenByToken(tokenToValidate);
    }
}
