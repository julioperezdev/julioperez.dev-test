package dev.julioperez.api.certificate.domain.port.createQrValidator;

import java.util.UUID;

public interface CreateQrValidatorContract {
    String createQrValidator(UUID certificateId);
}
