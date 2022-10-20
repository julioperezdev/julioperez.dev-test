package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginInputPort;
import dev.julioperez.api.shared.infrastructure.delivery.RestResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Slf4j
@AllArgsConstructor
public class SpringLoginUserController {

    private final LoginInputPort loginInputPort;
    @PostMapping
    public RestResponse<AuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest){
        AuthenticationResponse authenticationResponse = loginInputPort.loginUser(loginRequest);
        return new RestResponse<>(HttpStatus.ACCEPTED, authenticationResponse);
    }
}
