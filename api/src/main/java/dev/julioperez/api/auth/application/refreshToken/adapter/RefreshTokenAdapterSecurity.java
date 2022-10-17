package dev.julioperez.api.auth.application.refreshToken.adapter;

import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenSecurityOutputPort;
import dev.julioperez.api.auth.infrastructure.app.security.JwtProvider;

import java.util.Calendar;

public class RefreshTokenAdapterSecurity implements RefreshTokenSecurityOutputPort {

    private final JwtProvider jwtProvider;

    public RefreshTokenAdapterSecurity(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String generateTokeUsingEmail(String emailToRefresh) {
        return jwtProvider.generateTokenWithUserName(emailToRefresh);
    }

    @Override
    public Calendar getCalendarWithDateOfExpiration(){
        return jwtProvider.getCalendarWithDateOfExpiration();
    }
}
