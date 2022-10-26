package dev.julioperez.api.certificate.domain.port.createQrValidator;

import java.util.UUID;

public interface CreateQrValidatorOutputPort {
    String createQrValidator(UUID certificateId);
}
