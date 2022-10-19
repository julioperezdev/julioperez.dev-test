package dev.julioperez.api.auth.domain.port.enableUser;

import java.util.UUID;

public interface EnableUserInputPort {

    void enableUserByVerificationToken(UUID verificationToken);
}
