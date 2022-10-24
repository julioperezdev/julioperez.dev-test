package dev.julioperez.api.certificate.infrastructure.gateway;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;

public interface ITextRenderPdfContract {

    CertificateGenerated generatePdfFile(CertificateInformation certificateInformation);
}
