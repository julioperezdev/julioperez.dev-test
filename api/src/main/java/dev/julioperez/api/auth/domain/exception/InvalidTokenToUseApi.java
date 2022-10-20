package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;

public class InvalidTokenToUseApi extends DomainError {
    public InvalidTokenToUseApi() {
        super("Invalid token to use, need to re-authenticate it");
    }
}
