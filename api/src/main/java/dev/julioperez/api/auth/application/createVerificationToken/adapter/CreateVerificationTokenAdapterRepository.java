package dev.julioperez.api.auth.application.createVerificationToken.adapter;

import dev.julioperez.api.auth.application.modelMapper.VerificationTokenModelMapper;
import dev.julioperez.api.auth.domain.model.VerificationToken;
import dev.julioperez.api.auth.domain.port.createVerificationToken.CreateVerificationTokenOutputPort;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.VerificationTokenDao;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;

import java.util.Objects;

public class CreateVerificationTokenAdapterRepository implements CreateVerificationTokenOutputPort {

    private final VerificationTokenModelMapper verificationTokenModelMapper;
    private final VerificationTokenDao verificationTokenDao;
    private final UserDao userDao;

    public CreateVerificationTokenAdapterRepository(VerificationTokenModelMapper verificationTokenModelMapper, VerificationTokenDao verificationTokenDao, UserDao userDao) {
        this.verificationTokenModelMapper = verificationTokenModelMapper;
        this.verificationTokenDao = verificationTokenDao;
        this.userDao = userDao;
    }

    @Override
    public VerificationToken createVerificationToken(VerificationToken verificationToken) {
        UserEntity userEntity = userDao.findById(verificationToken.getUserId()).orElseThrow(RuntimeException::new);
        VerificationTokenEntity verificationTokenEntity = verificationTokenModelMapper.toVerificationTokenEntity(verificationToken, userEntity);
        verificationTokenDao.saveAndFlush(verificationTokenEntity);
        if(hasVerificationTokenBeenCreated(verificationTokenEntity)) throw new RuntimeException("Verification token has not been created in database");
        return verificationTokenModelMapper.toVerificationTokenModel(verificationTokenEntity);
    }

    private boolean hasVerificationTokenBeenCreated(VerificationTokenEntity verificationTokenEntity){
        return Objects.isNull(verificationTokenEntity.getId());
    }
}
