package dev.julioperez.api.auth.application.enableUser.service;

import dev.julioperez.api.auth.domain.model.User;
import dev.julioperez.api.auth.domain.port.enableUser.EnableUserContract;
import dev.julioperez.api.auth.domain.port.enableUser.EnableUserOutputPort;

import java.util.UUID;

public class EnableUserService implements EnableUserContract {
    private final EnableUserOutputPort enableUserOutputPort;

    public EnableUserService(EnableUserOutputPort enableUserOutputPort) {
        this.enableUserOutputPort = enableUserOutputPort;
    }

    @Override
    public void enableUserByVerificationToken(UUID verificationToken) {
        User user = enableUserOutputPort.enableUserByVerificationToken(verificationToken);
        if(!user.isEnable()) throw new RuntimeException();
    }
}
