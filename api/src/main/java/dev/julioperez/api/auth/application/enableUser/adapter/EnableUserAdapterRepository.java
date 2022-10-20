package dev.julioperez.api.auth.application.enableUser.adapter;

import dev.julioperez.api.auth.domain.exception.UserDoesNotExist;
import dev.julioperez.api.auth.domain.exception.UserWasEnableItBefore;
import dev.julioperez.api.auth.domain.exception.VerificationTokenDoesNotExist;
import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.enableUser.EnableUserOutputPort;
import dev.julioperez.api.auth.domain.port.mapper.UserMapper;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.VerificationTokenDao;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class EnableUserAdapterRepository implements EnableUserOutputPort {

    private final UserDao userDao;
    private final VerificationTokenDao verificationTokenDao;
    private final UserMapper userMapper;

    public EnableUserAdapterRepository(UserDao userDao, VerificationTokenDao verificationTokenDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.verificationTokenDao = verificationTokenDao;
        this.userMapper = userMapper;
    }

    @Override
    public User enableUserByVerificationToken(UUID verificationToken) {
        VerificationTokenEntity verificationTokenEntity = verificationTokenDao
                .findFirstByToken(verificationToken)
                .orElseThrow(VerificationTokenDoesNotExist::new);
        UserEntity userEntity = userDao
                .findById(verificationTokenEntity.getUser().getId()).orElseThrow(UserDoesNotExist::new);
        if(userEntity.getEnable()) throw new UserWasEnableItBefore(userEntity.getEmail());
        userEntity.setEnable(Boolean.TRUE);
        userDao.save(userEntity);
        return userMapper.userEntityToUser(userEntity);
    }
}
