package dev.julioperez.api.certificate.domain.model;

import java.util.UUID;

public record CertificateInformation (
        UUID certificateId,
        String studentName,
        String courseName,
        String formattedCreatedAt,
        String qrValidatorPath){
}
