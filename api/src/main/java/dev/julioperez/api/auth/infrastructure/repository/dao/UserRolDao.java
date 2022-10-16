package dev.julioperez.api.auth.infrastructure.repository.dao;


import dev.julioperez.api.auth.infrastructure.repository.model.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRolDao extends JpaRepository<UserRolEntity, UUID> {

}
