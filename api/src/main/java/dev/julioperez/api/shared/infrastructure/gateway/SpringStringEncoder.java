package dev.julioperez.api.shared.infrastructure.gateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SpringStringEncoder {

    private final PasswordEncoder passwordEncoder;

    public Optional<String> encodeString(String stringToEncode){
        try{
            return Optional.ofNullable(passwordEncoder.encode(stringToEncode));
        }catch (Exception exception){
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }
}
