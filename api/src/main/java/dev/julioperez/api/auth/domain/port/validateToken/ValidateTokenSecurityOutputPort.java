package dev.julioperez.api.auth.domain.port.validateToken;

public interface ValidateTokenSecurityOutputPort {

    boolean isNotValidTokenByToken(String tokenToValidate);
}
