package dev.julioperez.api.shared.application.encodeString.adapter;

import dev.julioperez.api.shared.domain.port.StringEncoderOutputPort;
import dev.julioperez.api.shared.infrastructure.gateway.SpringStringEncoder;

public class StringEncoderAdapter implements StringEncoderOutputPort {
    private final SpringStringEncoder springStringEncoder;

    public StringEncoderAdapter(SpringStringEncoder springStringEncoder) {
        this.springStringEncoder = springStringEncoder;
    }

    public String encodeString(String stringToEncode){
        return springStringEncoder.encodeString(stringToEncode);
    }
}
