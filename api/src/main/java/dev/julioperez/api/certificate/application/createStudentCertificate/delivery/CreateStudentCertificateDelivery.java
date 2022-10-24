package dev.julioperez.api.certificate.application.createStudentCertificate.delivery;

import dev.julioperez.api.certificate.domain.model.StudentCertificate;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateContract;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateInputPort;
import dev.julioperez.api.certificate.domain.port.mapper.StudentCertificateMapper;

public class CreateStudentCertificateDelivery implements CreateStudentCertificateInputPort {

    private final CreateStudentCertificateContract createStudentCertificateContract;
    private final StudentCertificateMapper studentCertificateMapper;

    public CreateStudentCertificateDelivery(CreateStudentCertificateContract createStudentCertificateContract, StudentCertificateMapper studentCertificateMapper) {
        this.createStudentCertificateContract = createStudentCertificateContract;
        this.studentCertificateMapper = studentCertificateMapper;
    }

    @Override
    public void createStudentCertificate(StudentCertificateToCreate studentCertificateToCreate) {
        StudentCertificate studentCertificate = studentCertificateMapper.toStudentCertificate(studentCertificateToCreate);
        createStudentCertificateContract.createStudentCertificate(studentCertificate);
    }
}
