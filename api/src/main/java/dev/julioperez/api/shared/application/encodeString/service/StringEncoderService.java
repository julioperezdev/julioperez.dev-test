package dev.julioperez.api.shared.application.encodeString.service;

import dev.julioperez.api.shared.domain.exception.StringEncoderException;
import dev.julioperez.api.shared.domain.port.StringEncoderContract;
import dev.julioperez.api.shared.domain.port.StringEncoderOutputPort;

public class StringEncoderService implements StringEncoderContract {

    private final StringEncoderOutputPort stringEncoderOutputPort;

    public StringEncoderService(StringEncoderOutputPort stringEncoderOutputPort) {
        this.stringEncoderOutputPort = stringEncoderOutputPort;
    }

    @Override
    public String encodeString(String stringToEncode) {
        return stringEncoderOutputPort.encodeString(stringToEncode)
                .orElseThrow(StringEncoderException::new);
    }
}
