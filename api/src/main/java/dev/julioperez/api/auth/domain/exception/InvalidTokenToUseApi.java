package dev.julioperez.api.auth.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidTokenToUseApi extends RuntimeException{
    public InvalidTokenToUseApi() {
        log.error("Invalid token to use, need to re-authenticate it");
    }
}
