package dev.julioperez.api.shared.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainError extends RuntimeException{

    public DomainError(String message) {
        super(message);
    }
}
