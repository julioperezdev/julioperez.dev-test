package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@Slf4j
public class SpringLoginUserController {

    private final LoginInputPort loginInputPort;

    public SpringLoginUserController(LoginInputPort loginInputPort) {
        this.loginInputPort = loginInputPort;
    }

    @GetMapping
    public AuthenticationResponse loginUser(){
        LoginRequest loginRequest = new LoginRequest("julio@email.com", "951");
        return loginInputPort.loginUser(loginRequest);
    }
}
