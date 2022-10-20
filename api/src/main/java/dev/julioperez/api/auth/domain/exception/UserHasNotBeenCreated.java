package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserHasNotBeenCreated extends DomainError {
    public UserHasNotBeenCreated() {
        super("The user has not been created");
        log.error(getMessage());
    }
}
