package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRolEntityDoesNotExist extends DomainError {

    public UserRolEntityDoesNotExist() {
        super("User rol entity does not exist");
        log.error(getMessage());
    }
}
