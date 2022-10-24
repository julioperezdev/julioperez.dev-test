package dev.julioperez.api.certificate.domain.port.createStudentCertificate;

import dev.julioperez.api.certificate.domain.model.StudentCertificate;

public interface CreateStudentCertificateOutputPort {
    StudentCertificate createStudentCertificate(StudentCertificate studentCertificate);
}
