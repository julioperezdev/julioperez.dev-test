package dev.julioperez.api.auth.infrastructure.delivery;

import dev.julioperez.api.auth.domain.port.enableUser.EnableUserInputPort;
import dev.julioperez.api.shared.infrastructure.delivery.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enableUser")
@Slf4j
public class SpringEnableUserController {
    private final EnableUserInputPort enableUserInputPort;

    public SpringEnableUserController(EnableUserInputPort enableUserInputPort) {
        this.enableUserInputPort = enableUserInputPort;
    }

    @GetMapping
    public RestResponse<?> enableUserByVerificationToken(){
        UUID uuidVerificationToken;
        try{
            uuidVerificationToken = UUID.fromString("d515cd94-a686-4218-8786-007e12631420");
        }catch (Exception exception){
            throw new RuntimeException();
        }
        enableUserInputPort.enableUserByVerificationToken(uuidVerificationToken);
        return new RestResponse<>(HttpStatus.OK);
    }
}
