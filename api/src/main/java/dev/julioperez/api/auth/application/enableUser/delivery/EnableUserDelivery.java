package dev.julioperez.api.auth.application.enableUser.delivery;

import dev.julioperez.api.auth.domain.port.enableUser.EnableUserContract;
import dev.julioperez.api.auth.domain.port.enableUser.EnableUserInputPort;

import java.util.UUID;

public class EnableUserDelivery implements EnableUserInputPort {
    private final EnableUserContract enableUserContract;

    public EnableUserDelivery(EnableUserContract enableUserContract) {
        this.enableUserContract = enableUserContract;
    }

    @Override
    public void enableUserByVerificationToken(UUID verificationToken) {
        enableUserContract.enableUserByVerificationToken(verificationToken);
    }
}
