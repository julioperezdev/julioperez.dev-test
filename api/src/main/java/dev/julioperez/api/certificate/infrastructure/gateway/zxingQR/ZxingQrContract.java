package dev.julioperez.api.certificate.infrastructure.gateway.zxingQR;

import java.util.UUID;

public interface ZxingQrContract {
    String createQrToCertificate(UUID certificateId);
}
