package dev.julioperez.api.emailNotifier.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorOccurredWhenSendingEmailException extends RuntimeException{
    public ErrorOccurredWhenSendingEmailException(String personToSend) {
        log.error("Exception occurred when sending mail to {}", personToSend);
    }
}
