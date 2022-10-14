package dev.julioperez.api.auth.domain.port.createVerificationToken;

import dev.julioperez.api.auth.domain.model.VerificationToken;
import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;

public interface CreateVerificationTokenOutputPort {

    VerificationToken createVerificationToken(VerificationToken verificationToken);
}
