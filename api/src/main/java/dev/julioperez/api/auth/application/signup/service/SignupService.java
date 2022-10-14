package dev.julioperez.api.auth.application.signup.service;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.createVerificationToken.CreateVerificationTokenContract;
import dev.julioperez.api.auth.domain.port.signup.SignupContract;
import dev.julioperez.api.auth.domain.port.signup.SignupOutputPort;
import dev.julioperez.api.shared.domain.port.StringEncoderContract;

public class SignupService implements SignupContract {


    private final SignupOutputPort signupRepository;
    private final CreateVerificationTokenContract createToken;
    private final StringEncoderContract stringEncoder;

    public SignupService(SignupOutputPort signupRepository, CreateVerificationTokenContract createToken, StringEncoderContract stringEncoder) {
        this.signupRepository = signupRepository;
        this.createToken = createToken;
        this.stringEncoder = stringEncoder;
    }


    @Override
    public Boolean signup(User user) {
        User userWithEncodedPassword = encodePasswordByRegisterRequest(user);
        User createdUser = signupRepository.signupUser(userWithEncodedPassword);
        //todo: call to generateVerificationToken
        String verificationTokenCreated = createToken.createVerificationToken(createdUser);
        //todo: call to sentEmail
        return null;
    }

    private User encodePasswordByRegisterRequest(User userBeforeToSignup){
        userBeforeToSignup.setPassword(stringEncoder.encodeString(userBeforeToSignup.getPassword()));
        return userBeforeToSignup;
    }
}
