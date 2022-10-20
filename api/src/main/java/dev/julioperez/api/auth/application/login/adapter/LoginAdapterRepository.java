package dev.julioperez.api.auth.application.login.adapter;

import dev.julioperez.api.auth.domain.exception.UserDoesNotExist;
import dev.julioperez.api.auth.domain.port.login.LoginRepositoryOutputPort;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserDao;

public class LoginAdapterRepository implements LoginRepositoryOutputPort {
    private final UserDao userDao;

    public LoginAdapterRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUserByEmailToValidateIfUserExist(String userEmail) {
        userDao.findFirstByEmail(userEmail).orElseThrow(() -> new UserDoesNotExist(userEmail));
    }
}
