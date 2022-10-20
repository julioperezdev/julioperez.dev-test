package dev.julioperez.api.auth.domain.port.validateToken;

public interface ValidateTokenContract {

    boolean isNotValidTokenByToken(String tokenToValidate);
}
