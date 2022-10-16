package dev.julioperez.api.auth.application.signup.adapter;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.signup.SignupOutputPort;
import dev.julioperez.api.auth.domain.port.mapper.UserMapper;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserRolDao;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.UserRolEntity;

import java.util.Objects;
import java.util.UUID;

public class SignupAdapterRepository implements SignupOutputPort {

    private final UserDao userDao;
    private final UserRolDao userRolDao;
    private final UserMapper userMapper;

    public SignupAdapterRepository(UserDao userDao, UserRolDao userRolDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userRolDao = userRolDao;
        this.userMapper = userMapper;
    }

    @Override
    public User signupUser(User userToSignup) {
        UserRolEntity userRolEntityResponse = getUserRolEntityByUserToSignupIdRol(userToSignup.getIdRol());
        if(isNotCorrectUserIdRol(userToSignup,userRolEntityResponse)) throw new RuntimeException("Cant follow process because id rol not is to usual user");
        UserEntity userEntity = userMapper.userModelToUserEntity(userToSignup, userRolEntityResponse);
        if(isErrorBecauseUserEmailWasGenerated(userToSignup.getEmail())) throw new RuntimeException(String.format("The user with email %s exist, cant be created again", userEntity.getEmail()));
        userDao.saveAndFlush(userEntity);
        if(hasUserBeenCreated(userEntity)) throw new RuntimeException("The user has not been created");
        return userMapper.userEntityToUser(userEntity);
    }

    private UserRolEntity getUserRolEntityByUserToSignupIdRol(UUID userIdRol){
        return userRolDao.findById(userIdRol).orElseThrow(RuntimeException::new);
    }

    private boolean isErrorBecauseUserEmailWasGenerated(String userEmail){
        UserEntity userEntity = userDao.findFirstByEmail(userEmail).orElse(null);
        return Objects.nonNull(userEntity);
    }

    private boolean isNotCorrectUserIdRol(User userToSignup, UserRolEntity userRol){
        return userToSignup.isNotEqualLikeUserIdRol(userRol.getId());
    }

    private boolean hasUserBeenCreated(UserEntity userEntity){
        return Objects.isNull(userEntity.getId());
    }
}