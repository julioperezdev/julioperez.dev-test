package dev.julioperez.api.auth.domain.port.enableUser;

import dev.julioperez.api.auth.domain.model.User;

import java.util.UUID;

public interface EnableUserOutputPort {

    User enableUserByVerificationToken(UUID verificationToken);
}
