package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserHasNotEnableIt extends DomainError {
    public UserHasNotEnableIt(String userEmail) {
        super(String.format("user %s has not been enable it", userEmail));
        log.error(getMessage());
    }
}
