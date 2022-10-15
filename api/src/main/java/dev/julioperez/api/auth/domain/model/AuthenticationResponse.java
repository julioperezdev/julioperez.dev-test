package dev.julioperez.api.auth.domain.model;

import java.util.Calendar;

public record AuthenticationResponse(String tokenAuthentication, String refreshToken, Calendar expireAt) {
}
