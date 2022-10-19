package dev.julioperez.api.auth.application.login.service;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginContract;
import dev.julioperez.api.auth.domain.port.login.LoginSecurityOutputPort;
import dev.julioperez.api.auth.domain.port.mapper.AuthenticationResponseMapper;
import dev.julioperez.api.auth.domain.port.refreshToken.RefreshTokenContract;

import java.util.Calendar;

public class LoginService implements LoginContract {


    private final RefreshTokenContract refreshToken;
    private final LoginSecurityOutputPort loginSecurity;
    private final AuthenticationResponseMapper authenticationResponseMapper;

    public LoginService(RefreshTokenContract refreshToken, LoginSecurityOutputPort loginSecurity, AuthenticationResponseMapper authenticationResponseMapper) {
        this.refreshToken = refreshToken;
        this.loginSecurity = loginSecurity;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
        String generatedToken = loginSecurity.generateTokenByLoginRequest(loginRequest);
        //String generatedRefreshToken = refreshToken.generateRefreshToken().getToken();
        Calendar generatedCalendarWithNewExpireDate = loginSecurity.getCalendarWithDateOfExpiration();
        return authenticationResponseMapper.toAuthenticationResponse(
                generatedToken,
                generatedCalendarWithNewExpireDate);
    }
}
