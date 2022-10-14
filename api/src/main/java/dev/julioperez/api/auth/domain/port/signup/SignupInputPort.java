package dev.julioperez.api.auth.domain.port.signup;

import dev.julioperez.api.auth.application.signup.delivery.SignupRequest;

public interface SignupInputPort {

    String signupUser(SignupRequest signupRequest);
}
