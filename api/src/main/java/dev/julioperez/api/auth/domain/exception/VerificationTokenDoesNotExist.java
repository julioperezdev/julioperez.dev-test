package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerificationTokenDoesNotExist extends DomainError {
    public VerificationTokenDoesNotExist() {
        super("Verification token does exist");
        log.error(getMessage());
    }
}
