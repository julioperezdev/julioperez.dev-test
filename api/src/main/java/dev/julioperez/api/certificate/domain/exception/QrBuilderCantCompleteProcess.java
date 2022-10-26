package dev.julioperez.api.certificate.domain.exception;

import dev.julioperez.api.shared.domain.exception.DomainError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QrBuilderCantCompleteProcess extends DomainError {

    public QrBuilderCantCompleteProcess() {
        super("Qr builder can not complete process");
        log.error(getMessage());
    }
}
