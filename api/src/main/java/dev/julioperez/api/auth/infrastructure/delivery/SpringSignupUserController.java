package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.application.signup.delivery.SignupRequest;
import dev.julioperez.api.auth.domain.port.signup.SignupInputPort;
import dev.julioperez.api.shared.infrastructure.delivery.RestResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public RestResponse<String> signupUser(@RequestBody SignupRequest signupRequest){
        SignupRequest signupRequestLocal = new SignupRequest(
                UUID.randomUUID(),
                signupRequest.email(),
                signupRequest.password());

        signupInputPort.signupUser(signupRequestLocal);
        return new RestResponse<>(HttpStatus.CREATED);
    }
}
