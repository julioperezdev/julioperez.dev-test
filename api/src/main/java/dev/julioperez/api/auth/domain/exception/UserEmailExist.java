package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserEmailExist extends DomainError {
    public UserEmailExist(String email) {
        super(String.format("The user with email %s exist, cant be created again", email));
        log.error(getMessage());
    }
}
