package dev.julioperez.api.auth.domain.port.signup;

import dev.julioperez.api.auth.domain.model.User;

public interface SignupOutputPort {
    User signupUser(User userToSignup);
}
