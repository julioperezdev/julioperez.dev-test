package dev.julioperez.api.certificate.domain.port.mapper;

import dev.julioperez.api.certificate.application.createStudentCertificate.delivery.StudentCertificateToCreate;
import dev.julioperez.api.certificate.domain.model.StudentCertificate;
import dev.julioperez.api.certificate.infrastructure.repository.model.StudentCertificateEntity;

public interface StudentCertificateMapper {
    StudentCertificate toStudentCertificate(StudentCertificateToCreate studentCertificateToCreate);
    StudentCertificate toStudentCertificate(StudentCertificateEntity studentCertificateEntity);
    StudentCertificateEntity toStudentCertificateEntity(StudentCertificate studentCertificate);
}
