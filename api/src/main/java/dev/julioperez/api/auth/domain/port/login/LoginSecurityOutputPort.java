package dev.julioperez.api.auth.domain.port.login;

import dev.julioperez.api.auth.domain.model.LoginRequest;

import java.util.Calendar;

public interface LoginSecurityOutputPort {

    String generateTokenByLoginRequest(LoginRequest loginRequest);

    Long getJwtExpirationInMillisToAuthenticationResponse();

    Calendar getCalendarWithDateOfExpiration();
}
