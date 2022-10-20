package dev.julioperez.api.auth.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdRolDontMatchWithUsualUser extends DomainError {
    public IdRolDontMatchWithUsualUser() {
        super("IdRol dont match with usual User");
        log.error(getMessage());
    }
}
