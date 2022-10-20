package dev.julioperez.api.auth.application.signup.service;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.createVerificationToken.CreateVerificationTokenContract;
import dev.julioperez.api.auth.domain.port.signup.SignupContract;
import dev.julioperez.api.auth.domain.port.signup.SignupOutputPort;
import dev.julioperez.api.emailNotifier.application.sendValidateUserEmail.service.SendValidateUserEmailService;
import dev.julioperez.api.emailNotifier.domain.model.EmailRequest;
import dev.julioperez.api.emailNotifier.domain.port.SendValidateUserEmailContract;
import dev.julioperez.api.shared.domain.port.StringEncoderContract;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class SignupService implements SignupContract {


    private final SignupOutputPort signupRepository;
    private final CreateVerificationTokenContract createToken;
    private final SendValidateUserEmailContract sendValidateUserEmail;
    private final StringEncoderContract stringEncoder;

    public SignupService(SignupOutputPort signupRepository, CreateVerificationTokenContract createToken, SendValidateUserEmailService sendValidateUserEmail, StringEncoderContract stringEncoder) {
        this.signupRepository = signupRepository;
        this.createToken = createToken;
        this.sendValidateUserEmail = sendValidateUserEmail;
        this.stringEncoder = stringEncoder;
    }


    @Override
    public void signup(User user) {
        log.info("start signup service");
        User userWithEncodedPassword = encodePasswordByRegisterRequest(user);
        User createdUser = signupRepository.signupUser(userWithEncodedPassword);
        UUID verificationTokenCreated = createToken.createVerificationToken(createdUser);
        sendValidateUserEmail.sendValidateUserEmail(new EmailRequest(createdUser.getEmail(),verificationTokenCreated));
    }

    private User encodePasswordByRegisterRequest(User userBeforeToSignup){
        userBeforeToSignup.setPassword(stringEncoder.encodeString(userBeforeToSignup.getPassword()));
        return userBeforeToSignup;
    }
}
