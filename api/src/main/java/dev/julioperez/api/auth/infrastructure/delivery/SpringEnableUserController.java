package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.domain.model.VerificationTokenRequest;
import dev.julioperez.api.auth.domain.port.enableUser.EnableUserInputPort;
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
@RequestMapping("/api/v1/enableUser")
@Slf4j
@AllArgsConstructor
public class SpringEnableUserController {
    private final EnableUserInputPort enableUserInputPort;

    @GetMapping
    public RestResponse enableUserByVerificationToken(@RequestBody VerificationTokenRequest token){
        UUID uuidVerificationToken;
        try{
            uuidVerificationToken = UUID.fromString(token.getToken());
        }catch (Exception exception){
            throw new RuntimeException();
        }
        enableUserInputPort.enableUserByVerificationToken(uuidVerificationToken);
        return new RestResponse<>(HttpStatus.OK);
    }
}
