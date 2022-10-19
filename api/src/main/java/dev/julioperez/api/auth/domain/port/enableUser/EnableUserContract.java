package dev.julioperez.api.auth.domain.port.enableUser;

import java.util.UUID;

public interface EnableUserContract {
    void enableUserByVerificationToken(UUID verificationToken);
}
