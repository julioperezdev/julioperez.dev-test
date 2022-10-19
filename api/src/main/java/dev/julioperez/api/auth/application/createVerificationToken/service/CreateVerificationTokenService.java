package dev.julioperez.api.auth.application.createVerificationToken.service;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.model.VerificationToken;
import dev.julioperez.api.auth.domain.port.createVerificationToken.CreateVerificationTokenContract;
import dev.julioperez.api.auth.domain.port.createVerificationToken.CreateVerificationTokenOutputPort;
import dev.julioperez.api.auth.domain.port.mapper.VerificationTokenMapper;

import java.util.UUID;

public class CreateVerificationTokenService implements CreateVerificationTokenContract {

    private final CreateVerificationTokenOutputPort createVerificationTokenOutputPort;
    private final VerificationTokenMapper verificationTokenMapper;

    public CreateVerificationTokenService(CreateVerificationTokenOutputPort createVerificationTokenOutputPort, VerificationTokenMapper verificationTokenMapper) {
        this.createVerificationTokenOutputPort = createVerificationTokenOutputPort;
        this.verificationTokenMapper = verificationTokenMapper;
    }

    @Override
    public UUID createVerificationToken(User createdUser) {
        UUID token = UUID.randomUUID();
        VerificationToken verificationToken = verificationTokenMapper.toVerificationTokenModel(createdUser, token);
        verificationToken = createVerificationTokenOutputPort.createVerificationToken(verificationToken);
        return verificationToken.getToken();
    }
}
