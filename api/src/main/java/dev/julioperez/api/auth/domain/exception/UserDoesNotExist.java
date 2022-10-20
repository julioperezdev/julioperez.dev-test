package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDoesNotExist extends DomainError {

    public UserDoesNotExist() {
        super("User does not exist in database");
        log.error(getMessage());
    }
    public UserDoesNotExist(String userEmail) {
        super(String.format("User with email %s does not exist in database",userEmail));
        log.error(getMessage());
    }
}
