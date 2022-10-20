package dev.julioperez.api.auth.application.validateToken.adapter;

import dev.julioperez.api.auth.domain.port.validateToken.ValidateTokenSecurityOutputPort;
import dev.julioperez.api.auth.infrastructure.app.security.JwtProvider;

public class ValidateTokenSecurityAdapter implements ValidateTokenSecurityOutputPort {

    private final JwtProvider jwtProvider;

    public ValidateTokenSecurityAdapter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean isNotValidTokenByToken(String tokenToValidate){
        return jwtProvider.isNotValidTokenToRefresh(tokenToValidate);
    }
}
