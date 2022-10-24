package dev.julioperez.api.certificate.application.modelMapper;

import dev.julioperez.api.certificate.application.createStudentCertificate.delivery.StudentCertificateToCreate;
import dev.julioperez.api.certificate.domain.model.StudentCertificate;
import dev.julioperez.api.certificate.domain.port.mapper.StudentCertificateMapper;
import dev.julioperez.api.certificate.infrastructure.repository.model.StudentCertificateEntity;

import java.util.Date;

public class StudentCertificateModelMapper implements StudentCertificateMapper {

    @Override
    public StudentCertificate toStudentCertificate(StudentCertificateToCreate studentCertificateToCreate) {
        Date createdAt = new Date();
        return new StudentCertificate(
                studentCertificateToCreate.id(),
                studentCertificateToCreate.studentId(),
                studentCertificateToCreate.studentName(),
                studentCertificateToCreate.courseId(),
                studentCertificateToCreate.courseName(),
                createdAt);
    }

    @Override
    public StudentCertificate toStudentCertificate(StudentCertificateEntity studentCertificateEntity) {
        return new StudentCertificate(
                studentCertificateEntity.getId(),
                studentCertificateEntity.getStudentId(),
                studentCertificateEntity.getStudentName(),
                studentCertificateEntity.getCourseId(),
                studentCertificateEntity.getCourseName(),
                studentCertificateEntity.getCreatedAt(),
                studentCertificateEntity.getCertificateHash());
    }

    @Override
    public StudentCertificateEntity toStudentCertificateEntity(StudentCertificate studentCertificate) {
        return new StudentCertificateEntity(
                studentCertificate.getId(),
                studentCertificate.getStudentId(),
                studentCertificate.getStudentName(),
                studentCertificate.getCourseId(),
                studentCertificate.getCourseName(),
                studentCertificate.getCreatedAt(),
                null);
    }
}
