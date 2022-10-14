package dev.julioperez.api.auth.domain.port.signup;

import dev.julioperez.api.auth.domain.model.User;

public interface SignupContract {

    Boolean signup(User user);
}
