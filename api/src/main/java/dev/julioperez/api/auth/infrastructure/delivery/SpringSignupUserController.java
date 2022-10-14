package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.application.signup.delivery.SignupRequest;
import dev.julioperez.api.auth.domain.port.signup.SignupInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/signup")
@AllArgsConstructor
@Slf4j
public class SpringSignupUserController {

    private final SignupInputPort signupInputPort;

    @GetMapping
    public void signupUser(){
        String email = UUID
                .randomUUID()
                .toString()
                .substring(0,4)
                .concat("@email.com");
        SignupRequest signupRequest = new SignupRequest(
                UUID.randomUUID(),
                email,
                "password");
        signupInputPort.signupUser(signupRequest);
        log.info("listo");
    }
}
