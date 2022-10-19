package dev.julioperez.api.auth.application.modelMapper;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.port.mapper.AuthenticationResponseMapper;

import java.util.Calendar;

public class AuthenticationResponseModelMapper implements AuthenticationResponseMapper {

    @Override
    public AuthenticationResponse toAuthenticationResponse(String generatedToken,Calendar generatedCalendarWithNewExpireDate) {
        return new AuthenticationResponse(
                generatedToken,
                generatedCalendarWithNewExpireDate);
    }
}
