package dev.julioperez.api.emailNotifier.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorOccurredWhenSendingEmailException extends DomainError {
    public ErrorOccurredWhenSendingEmailException(String personToSend) {
        super(String.format("Exception occurred when sending mail to %s", personToSend));
        log.error(getMessage());
    }
}
