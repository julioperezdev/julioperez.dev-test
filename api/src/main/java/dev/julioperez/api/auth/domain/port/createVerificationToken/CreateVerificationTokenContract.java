package dev.julioperez.api.auth.domain.port.createVerificationToken;

import dev.julioperez.api.auth.domain.model.User;

public interface CreateVerificationTokenContract {

    String createVerificationToken(User createdUser);
}
