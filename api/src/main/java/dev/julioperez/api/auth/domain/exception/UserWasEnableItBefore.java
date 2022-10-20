package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserWasEnableItBefore extends DomainError {
    public UserWasEnableItBefore(String userEmail) {
        super(String.format("User %s was enable it before", userEmail));
        log.error(getMessage());
    }
}
