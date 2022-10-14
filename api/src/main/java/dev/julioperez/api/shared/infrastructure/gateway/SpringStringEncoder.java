package dev.julioperez.api.shared.infrastructure.gateway;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpringStringEncoder {

    private final PasswordEncoder passwordEncoder;

    public String encodeString(String stringToEncode){
        return passwordEncoder.encode(stringToEncode);
    }
}
