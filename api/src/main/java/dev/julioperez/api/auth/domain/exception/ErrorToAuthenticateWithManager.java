package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorToAuthenticateWithManager extends DomainError {
    public ErrorToAuthenticateWithManager(String email,String message) {
        super(message);
        log.error("User {} try do login but does have error -> {}", email, getMessage());
    }
}
