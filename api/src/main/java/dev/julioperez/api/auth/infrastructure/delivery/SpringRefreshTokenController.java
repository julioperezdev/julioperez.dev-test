package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.RefreshTokenRequest;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenInputPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/refreshToken")
@AllArgsConstructor
public class SpringRefreshTokenController {

    private final RefreshTokenInputPort refreshTokenInputPort;

    @GetMapping
    public AuthenticationResponse refreshToken(){
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(
                null,
                null);
        return refreshTokenInputPort.refreshToken(refreshTokenRequest);
    }
}
