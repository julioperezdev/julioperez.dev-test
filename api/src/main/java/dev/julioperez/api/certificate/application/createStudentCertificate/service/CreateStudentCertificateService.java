package dev.julioperez.api.certificate.application.createStudentCertificate.service;

import dev.julioperez.api.certificate.domain.model.CertificateGenerated;
import dev.julioperez.api.certificate.domain.model.CertificateInformation;
import dev.julioperez.api.certificate.domain.model.StudentCertificate;
import dev.julioperez.api.certificate.domain.port.createQrValidator.CreateQrValidatorContract;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateContract;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateOutputPort;
import dev.julioperez.api.certificate.domain.port.generateCertificate.GenerateCertificateContract;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class CreateStudentCertificateService implements CreateStudentCertificateContract {

    private final CreateStudentCertificateOutputPort createStudentCertificateOutputPort;
    private final GenerateCertificateContract generateCertificateContract;
    private final CreateQrValidatorContract createQrValidatorContract;

    public CreateStudentCertificateService(CreateStudentCertificateOutputPort createStudentCertificateOutputPort,GenerateCertificateContract generateCertificateContract,CreateQrValidatorContract createQrValidatorContract) {
        this.createStudentCertificateOutputPort = createStudentCertificateOutputPort;
        this.generateCertificateContract=generateCertificateContract;
        this.createQrValidatorContract = createQrValidatorContract;
    }

    @Override
    public void createStudentCertificate(StudentCertificate studentCertificate) {
        log.info("empieza el createStudentCertificate");
        StudentCertificate studentCertificateCreated = createStudentCertificateOutputPort.createStudentCertificate(studentCertificate);
        log.info("termina el createStudentCertificate");
        //todo: create certificate
        String qrValidatorPath = createQrValidatorContract.createQrValidator(studentCertificateCreated.getId());
        String qr = "/Users/julio/developer/realProjects/julioperez.dev/api/".concat(qrValidatorPath);
        log.info("el path del QR ===========> {}", qrValidatorPath);
        log.info("el path del QR ===========> {}", qr);
        log.info("se mapea el CertificateInformation");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formatter.format(studentCertificateCreated.getCreatedAt());
        CertificateInformation certificateInformation = new CertificateInformation(
                studentCertificateCreated.getId(),
                studentCertificateCreated.getStudentName(),
                studentCertificateCreated.getCourseName(),
                dateFormatted,
                qrValidatorPath);
        log.info("empieza el generateCertificate");
        CertificateGenerated certificateGenerated = generateCertificateContract.generateCertificate(certificateInformation);
        log.info("El certificado tuvo un resutao {} para el courseId {} ",
                certificateGenerated.isCreated(),
                certificateGenerated.certificateId());
    }
}
