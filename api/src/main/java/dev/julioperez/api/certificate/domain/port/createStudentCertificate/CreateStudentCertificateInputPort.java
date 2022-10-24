package dev.julioperez.api.certificate.domain.port.createStudentCertificate;

import dev.julioperez.api.certificate.application.createStudentCertificate.delivery.StudentCertificateToCreate;

public interface CreateStudentCertificateInputPort {
    void createStudentCertificate(StudentCertificateToCreate studentCertificateToCreate);
}
