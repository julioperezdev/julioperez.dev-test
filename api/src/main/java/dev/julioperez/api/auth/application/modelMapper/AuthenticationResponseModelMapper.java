package dev.julioperez.api.auth.application.modelMapper;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.port.mapper.AuthenticationResponseMapper;

public class AuthenticationResponseModelMapper implements AuthenticationResponseMapper {

    @Override
    public AuthenticationResponse toAuthenticationResponse(String generatedToken) {
        return new AuthenticationResponse(
                generatedToken);
    }
}
