package dev.julioperez.api.shared.infrastructure.delivery;

import dev.julioperez.api.auth.domain.exception.*;
import dev.julioperez.api.emailNotifier.domain.exception.ErrorOccurredWhenSendingEmailException;
import dev.julioperez.api.shared.domain.exception.StringEncoderException;
import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    //auth
    INVALID_TOKEN_TO_USE_API(InvalidTokenToUseApi.class, HttpStatus.PRECONDITION_FAILED),
    INVALID_FIELDS_TO_SIGNUP_USER_REQUEST(InvalidFieldsToSignupUserRequest.class, HttpStatus.PRECONDITION_REQUIRED),
    USER_ROL_ENTITY_DOES_NOT_EXIST(UserRolEntityDoesNotExist.class, HttpStatus.BAD_REQUEST),
    USER_EMAIL_EXIST(UserEmailExist.class, HttpStatus.NOT_IMPLEMENTED),
    USER_WAS_ENABLE_IT_BEFORE(UserWasEnableItBefore.class, HttpStatus.NOT_IMPLEMENTED),
    USER_HAS_NOT_BEEN_ENABLE_IT(UserHasNotEnableIt.class, HttpStatus.BAD_REQUEST),
    USER_HAS_NOT_BEEN_CREATED(UserHasNotBeenCreated.class, HttpStatus.BAD_GATEWAY),
    USER_DOES_NOT_EXIST(UserDoesNotExist.class, HttpStatus.BAD_REQUEST),
    ERROR_TO_AUTHENTICATE_WITH_MANAGER(ErrorToAuthenticateWithManager.class, HttpStatus.UNAUTHORIZED),
    VERIFICATION_TOKEN_HAS_NOT_BEEN_CREATED(VerificationTokenHasNotBeenCreated.class, HttpStatus.BAD_GATEWAY),
    VERIFICATION_TOKEN_DOES_NOT_EXIST(VerificationTokenDoesNotExist.class, HttpStatus.BAD_REQUEST),

    //mail
    ERROR_OCCURRED_WHEN_SENDING_EMAIL(ErrorOccurredWhenSendingEmailException.class, HttpStatus.PARTIAL_CONTENT),

    //shared
    STRING_ENCODER_EXCEPTION(StringEncoderException.class, HttpStatus.NOT_IMPLEMENTED);

    private Class<? extends Throwable> exceptionClass;
    private HttpStatus status;
    private SupportedExceptions(Class<? extends Throwable>exception, HttpStatus status){
        this.exceptionClass = exception;
        this.status = status;
    }

    public Class <? extends Throwable> getExceptionClass(){
        return this.exceptionClass;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
