package dev.julioperez.api.auth.domain.port.mapper;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;

import java.util.Calendar;

public interface AuthenticationResponseMapper {

    AuthenticationResponse toAuthenticationResponse(
            String generatedToken,
            Calendar generatedCalendarWithNewExpireDate);
}
