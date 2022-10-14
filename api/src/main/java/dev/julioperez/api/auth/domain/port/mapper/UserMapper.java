package dev.julioperez.api.auth.domain.port.mapper;

import dev.julioperez.api.auth.application.signup.delivery.SignupRequest;
import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.UserRolEntity;

public interface UserMapper {
    User signupRequestToUser(SignupRequest signupRequest);

    UserEntity userModelToUserEntity(User user, UserRolEntity userRolEntity);

    User userEntityToUser(UserEntity userEntity);
}
