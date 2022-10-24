package dev.julioperez.api.certificate.application.createStudentCertificate.service;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;
import dev.julioperez.api.certificate.domain.model.StudentCertificate;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateContract;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateOutputPort;
import dev.julioperez.api.certificate.domain.port.generateCertificate.GenerateCertificateContract;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateStudentCertificateService implements CreateStudentCertificateContract {

    private final CreateStudentCertificateOutputPort createStudentCertificateOutputPort;
    private final GenerateCertificateContract generateCertificateContract;

    public CreateStudentCertificateService(CreateStudentCertificateOutputPort createStudentCertificateOutputPort,GenerateCertificateContract generateCertificateContract) {
        this.createStudentCertificateOutputPort = createStudentCertificateOutputPort;
        this.generateCertificateContract=generateCertificateContract;
    }

    @Override
    public void createStudentCertificate(StudentCertificate studentCertificate) {
        log.info("empieza el createStudentCertificate");
        StudentCertificate studentCertificateCreated = createStudentCertificateOutputPort.createStudentCertificate(studentCertificate);
        log.info("termina el createStudentCertificate");
        //todo: create certificate
        log.info("se mapea el CertificateInformation");
        CertificateInformation certificateInformation = new CertificateInformation(
                studentCertificateCreated.getId(),
                studentCertificateCreated.getStudentName(),
                studentCertificateCreated.getCourseName(),
                studentCertificateCreated.getCreatedAt().toString());
        log.info("empieza el generateCertificate");
        CertificateGenerated certificateGenerated = generateCertificateContract.generateCertificate(certificateInformation);
        log.info("El certificado tuvo un resutao {} para el courseId {} ",
                certificateGenerated.isCreated(),
                certificateGenerated.certificateId());
    }
}
