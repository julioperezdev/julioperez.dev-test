package dev.julioperez.api.auth.application.modelMapper;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.model.VerificationToken;
import dev.julioperez.api.auth.domain.port.mapper.VerificationTokenMapper;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;

import java.util.Calendar;
import java.util.UUID;

public class VerificationTokenModelMapper implements VerificationTokenMapper {

    @Override
    public VerificationTokenEntity toVerificationTokenEntity(VerificationToken verificationToken, UserEntity userEntity) {
        return new VerificationTokenEntity(
                verificationToken.getId(),
                verificationToken.getToken(),
                userEntity,
                verificationToken.getExpiryDate());
    }

    @Override
    public VerificationToken toVerificationTokenModel(VerificationTokenEntity verificationTokenEntity) {
        return new VerificationToken(
                verificationTokenEntity.getId(),
                verificationTokenEntity.getToken(),
                verificationTokenEntity.getUser().getId(),
                verificationTokenEntity.getExpiryDate());
    }

    @Override
    public VerificationToken toVerificationTokenModel(User user, String token) {
        return new VerificationToken(
                UUID.randomUUID(),
                token,
                user.getId(),
                Calendar.getInstance()
        );
    }
}
