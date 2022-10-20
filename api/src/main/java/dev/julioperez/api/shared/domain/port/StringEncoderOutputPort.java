package dev.julioperez.api.shared.domain.port;

import java.util.Optional;

public interface StringEncoderOutputPort {
    Optional<String> encodeString(String stringToEncode);
}
