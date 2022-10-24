package dev.julioperez.api.certificate.infrastructure.repository.dao;

import dev.julioperez.api.certificate.infrastructure.repository.model.StudentCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentCertificateDao extends JpaRepository<StudentCertificateEntity, UUID> {
}
