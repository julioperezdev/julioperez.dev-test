package dev.julioperez.api.shared.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringEncoderException extends DomainError{

    public StringEncoderException() {
        super("String encoder has error");
        log.error(getMessage());
    }
}
