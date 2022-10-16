package dev.julioperez.api.auth.application.login.adapter;

import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginSecurityOutputPort;
import dev.julioperez.api.auth.infrastructure.app.security.JwtProvider;
import dev.julioperez.api.auth.infrastructure.app.security.ManagerAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Calendar;
import java.util.Objects;

@Slf4j
public class LoginAdapterSecurity implements LoginSecurityOutputPort {

    private final ManagerAuthenticator managerAuthenticator;
    private final JwtProvider jwtProvider;

    public LoginAdapterSecurity(ManagerAuthenticator managerAuthenticator, JwtProvider jwtProvider) {
        this.managerAuthenticator = managerAuthenticator;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String generateTokenByLoginRequest(LoginRequest loginRequest) {
        Authentication authentication = this.authenticateWithManager(loginRequest);
        if(Objects.isNull(authentication)) throw new RuntimeException(String.format("The user %s cant do the Login process because dont have enable validation", loginRequest.email()));
        return jwtProvider.generateToken(authentication);
    }

    private Authentication authenticateWithManager(LoginRequest loginRequest){
        try {
            Authentication authentication = managerAuthenticator.authenticateByEmailAndPassword(loginRequest);
            managerAuthenticator.setAuthenticationToSecurityContext(authentication);
            return authentication;
        }catch (Exception exception){
            log.error("User {} try do login but dont has been validated", loginRequest.email());
        }
        return null;
    }

    @Override
    public Long getJwtExpirationInMillisToAuthenticationResponse() {
        return jwtProvider.getJwtExpirationInMillis();
    }

    @Override
    public Calendar getCalendarWithDateOfExpiration(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.sum(
                System.currentTimeMillis(),
                getJwtExpirationInMillisToAuthenticationResponse()));
        return calendar;
    }
}
