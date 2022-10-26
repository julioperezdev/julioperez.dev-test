package dev.julioperez.api.certificate.application.createQrValidator.service;

import dev.julioperez.api.certificate.domain.port.createQrValidator.CreateQrValidatorContract;
import dev.julioperez.api.certificate.domain.port.createQrValidator.CreateQrValidatorOutputPort;

import java.util.UUID;

public class CreateQrValidatorService implements CreateQrValidatorContract {

    private final CreateQrValidatorOutputPort createQrValidatorOutputPort;

    public CreateQrValidatorService(CreateQrValidatorOutputPort createQrValidatorOutputPort) {
        this.createQrValidatorOutputPort = createQrValidatorOutputPort;
    }
    @Override
    public String createQrValidator(UUID certificateId) {
        return createQrValidatorOutputPort.createQrValidator(certificateId);
    }
}
