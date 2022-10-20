package dev.julioperez.api.auth.application.signup.adapter;

import dev.julioperez.api.auth.domain.exception.IdRolDontMatchWithUsualUser;
import dev.julioperez.api.auth.domain.exception.UserEmailExist;
import dev.julioperez.api.auth.domain.exception.UserHasNotBeenCreated;
import dev.julioperez.api.auth.domain.exception.UserRolEntityDoesNotExist;
import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.mapper.UserMapper;
import dev.julioperez.api.auth.domain.port.signup.SignupOutputPort;
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
        validateIfNotCorrectUserIdRol(userToSignup,userRolEntityResponse);
        UserEntity userEntity = userMapper.userModelToUserEntity(userToSignup, userRolEntityResponse);
        validateIfHasErrorBecauseUserEmailWasGenerated(userToSignup.getEmail());
        userDao.saveAndFlush(userEntity);
        validateIfHasUserBeenCreated(userEntity);
        return userMapper.userEntityToUser(userEntity);
    }

    private UserRolEntity getUserRolEntityByUserToSignupIdRol(UUID userIdRol){
        return userRolDao.findById(userIdRol).orElseThrow(UserRolEntityDoesNotExist::new);
    }

    private void validateIfHasErrorBecauseUserEmailWasGenerated(String userEmail){
        UserEntity userEntity = userDao.findFirstByEmail(userEmail).orElse(null);
        if(Objects.nonNull(userEntity)) throw new UserEmailExist(userEmail);
    }

    private void validateIfNotCorrectUserIdRol(User userToSignup, UserRolEntity userRol){
        if(userToSignup.isNotEqualLikeUserIdRol(userRol.getId())) throw new IdRolDontMatchWithUsualUser();
    }

    private void validateIfHasUserBeenCreated(UserEntity userEntity){
        if(Objects.isNull(userEntity.getId())) throw new UserHasNotBeenCreated();
    }
}
