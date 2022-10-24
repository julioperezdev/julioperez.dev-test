package dev.julioperez.api.certificate.application.generateCertificate.service;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;
import dev.julioperez.api.certificate.domain.port.generateCertificate.GenerateCertificateContract;
import dev.julioperez.api.certificate.domain.port.generateCertificate.GenerateCertificateOutputPort;

public class GenerateCertificateService implements GenerateCertificateContract {

    private final GenerateCertificateOutputPort generateCertificateOutputPort;

    public GenerateCertificateService(GenerateCertificateOutputPort generateCertificateOutputPort) {
        this.generateCertificateOutputPort = generateCertificateOutputPort;
    }

    @Override
    public CertificateGenerated generateCertificate(CertificateInformation certificateInformation) {
        return generateCertificateOutputPort.generateCertificate(certificateInformation);
    }
}
