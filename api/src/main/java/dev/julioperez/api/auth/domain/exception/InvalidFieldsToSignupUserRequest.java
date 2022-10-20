package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidFieldsToSignupUserRequest extends DomainError {
    public InvalidFieldsToSignupUserRequest() {
        super("Invalid fields to signup user request");
        log.error(getMessage());
    }
}
