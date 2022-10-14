package dev.julioperez.api.auth.infrastructure.repository.dao;


import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRolDao extends JpaRepository<UserRolEntity, Long> {

}
