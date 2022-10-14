package dev.julioperez.api.auth.domain.port.mapper;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.model.VerificationToken;
import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;

public interface VerificationTokenMapper {

    VerificationTokenEntity toVerificationTokenEntity(VerificationToken verificationToken, UserEntity userEntity);
    VerificationToken toVerificationTokenModel(VerificationTokenEntity verificationTokenEntity);

    VerificationToken toVerificationTokenModel(User user, String token);
}
