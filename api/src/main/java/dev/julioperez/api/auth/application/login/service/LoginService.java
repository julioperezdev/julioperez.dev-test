package dev.julioperez.api.auth.application.login.service;

import dev.julioperez.api.auth.domain.model.AuthenticationResponse;
import dev.julioperez.api.auth.domain.model.LoginRequest;
import dev.julioperez.api.auth.domain.port.login.LoginContract;
import dev.julioperez.api.auth.domain.port.login.LoginRepositoryOutputPort;
import dev.julioperez.api.auth.domain.port.login.LoginSecurityOutputPort;
import dev.julioperez.api.auth.domain.port.mapper.AuthenticationResponseMapper;

public class LoginService implements LoginContract {
    private final LoginRepositoryOutputPort loginRepository;
    private final LoginSecurityOutputPort loginSecurity;
    private final AuthenticationResponseMapper authenticationResponseMapper;

    public LoginService(LoginRepositoryOutputPort loginRepository, LoginSecurityOutputPort loginSecurity, AuthenticationResponseMapper authenticationResponseMapper) {
        this.loginRepository = loginRepository;
        this.loginSecurity = loginSecurity;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
        loginRepository.getUserByEmailToValidateIfUserExist(loginRequest.getEmail());
        String generatedToken = loginSecurity.generateTokenByLoginRequest(loginRequest);
        return authenticationResponseMapper.toAuthenticationResponse(generatedToken);
    }
}