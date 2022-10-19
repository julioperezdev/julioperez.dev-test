package dev.julioperez.api.auth.domain.port.createVerificationToken;

import dev.julioperez.api.auth.domain.model.User;

import java.util.UUID;

public interface CreateVerificationTokenContract {

    UUID createVerificationToken(User createdUser);
}
