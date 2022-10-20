package dev.julioperez.api.auth.application.signup.delivery;

import dev.julioperez.api.auth.application.modelMapper.UserModelMapper;
import dev.julioperez.api.auth.domain.exception.InvalidFieldsToSignupUserRequest;
import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.signup.SignupContract;
import dev.julioperez.api.auth.domain.port.signup.SignupInputPort;

public class SignupDelivery implements SignupInputPort {

    private final SignupContract signupContract;
    private final UserModelMapper userModelMapper;


    public SignupDelivery(SignupContract signupContract, UserModelMapper userModelMapper) {
        this.signupContract = signupContract;
        this.userModelMapper = userModelMapper;
    }


    @Override
    public void signupUser(SignupRequest signupRequest) {
        if(signupRequest.isInvalidFields()) throw new InvalidFieldsToSignupUserRequest();
        User userBeforeToSignup = userModelMapper.signupRequestToUser(signupRequest);
        signupContract.signup(userBeforeToSignup);
    }
}
