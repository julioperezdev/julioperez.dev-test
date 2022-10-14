package dev.julioperez.api.auth.application.signup.delivery;

import java.util.Objects;
import java.util.UUID;

public record SignupRequest(
        UUID id,
        String email,
        String password
) {
    public boolean isInvalidFields(){
        return Objects.isNull(id)
                || Objects.isNull(email)
                || Objects.isNull(password);
    }
}
