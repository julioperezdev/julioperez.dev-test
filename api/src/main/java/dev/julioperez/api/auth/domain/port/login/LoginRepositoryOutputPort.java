package dev.julioperez.api.auth.domain.port.login;

public interface LoginRepositoryOutputPort {
    void getUserByEmailToValidateIfUserExist(String userEmail);
}
