package dev.julioperez.api.auth.domain.port.signup;

import dev.julioperez.api.auth.domain.model.User;

public interface SignupContract {

    void signup(User user);
}
