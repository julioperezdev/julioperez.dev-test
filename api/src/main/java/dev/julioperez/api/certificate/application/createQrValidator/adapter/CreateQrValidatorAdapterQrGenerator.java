package dev.julioperez.api.certificate.application.createQrValidator.adapter;

import dev.julioperez.api.certificate.domain.port.createQrValidator.CreateQrValidatorOutputPort;
import dev.julioperez.api.certificate.infrastructure.gateway.zxingQR.ZxingQrContract;

import java.util.UUID;

public class CreateQrValidatorAdapterQrGenerator implements CreateQrValidatorOutputPort {
    private final ZxingQrContract zxingQrContract;

    public CreateQrValidatorAdapterQrGenerator(ZxingQrContract zxingQrContract) {
        this.zxingQrContract = zxingQrContract;
    }

    @Override
    public String createQrValidator(UUID certificateId) {
        return zxingQrContract.createQrToCertificate(certificateId);
    }
}
