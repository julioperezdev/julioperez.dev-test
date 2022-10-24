package dev.julioperez.api.certificate.domain.model;

import java.util.UUID;

public record CertificateGenerated(
        UUID certificateId,
        boolean isCreated,
        String certificateHash) {
}
