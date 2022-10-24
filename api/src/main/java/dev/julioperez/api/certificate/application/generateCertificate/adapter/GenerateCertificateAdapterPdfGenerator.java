package dev.julioperez.api.certificate.application.generateCertificate.adapter;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;
import dev.julioperez.api.certificate.domain.port.generateCertificate.GenerateCertificateOutputPort;
import dev.julioperez.api.certificate.infrastructure.gateway.ITextRenderPdfContract;

public class GenerateCertificateAdapterPdfGenerator implements GenerateCertificateOutputPort {

    private final ITextRenderPdfContract iTextRenderPdfContract;

    public GenerateCertificateAdapterPdfGenerator(ITextRenderPdfContract iTextRenderPdfContract) {
        this.iTextRenderPdfContract = iTextRenderPdfContract;
    }

    @Override
    public CertificateGenerated generateCertificate(CertificateInformation certificateInformation){
        return iTextRenderPdfContract.generatePdfFile(certificateInformation);
    }
}
