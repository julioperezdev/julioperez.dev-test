package dev.julioperez.api.auth.domain.port.mapper;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;

public interface AuthenticationResponseMapper {

    AuthenticationResponse toAuthenticationResponse(
            String generatedToken);
}
