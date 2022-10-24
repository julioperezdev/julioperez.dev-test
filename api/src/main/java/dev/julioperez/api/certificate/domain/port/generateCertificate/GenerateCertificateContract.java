package dev.julioperez.api.certificate.domain.port.generateCertificate;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;

public interface GenerateCertificateContract {
    CertificateGenerated generateCertificate(CertificateInformation certificateInformation);
}
