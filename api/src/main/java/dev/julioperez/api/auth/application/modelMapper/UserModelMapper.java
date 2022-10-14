package dev.julioperez.api.auth.application.modelMapper;

import dev.julioperez.api.auth.application.signup.delivery.SignupRequest;
import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.mapper.UserMapper;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.UserRolEntity;

import java.util.Calendar;

public class UserModelMapper implements UserMapper {

    @Override
    public User signupRequestToUser(SignupRequest signupRequest) {
        return new User(
                signupRequest.id(),
                signupRequest.password(),
                signupRequest.email(),
                Calendar.getInstance(),
                Boolean.FALSE,
                User.USER_ID_ROL);
    }

    public UserEntity userModelToUserEntity(User user, UserRolEntity userRolEntity){
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated(),
                user.isEnable(),
                userRolEntity);
    }

    @Override
    public User userEntityToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getCreated(),
                userEntity.getEnable(),
                userEntity.getUserRol().getId());
    }
}
