package dev.julioperez.api.auth.infrastructure.repository.dao;

import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenDao extends JpaRepository<VerificationTokenEntity, UUID> {

    Optional<VerificationTokenEntity> findFirstByToken(UUID token);
}
