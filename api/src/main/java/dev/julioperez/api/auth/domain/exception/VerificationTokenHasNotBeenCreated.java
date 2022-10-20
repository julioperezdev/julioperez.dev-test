package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerificationTokenHasNotBeenCreated extends DomainError {

    public VerificationTokenHasNotBeenCreated() {
        super("Verification token has not been created in database");
        log.error(getMessage());
    }
}
