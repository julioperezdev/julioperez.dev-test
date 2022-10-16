package dev.julioperez.api.emailNotifier.domain.port;

import dev.julioperez.api.emailNotifier.domain.model.EmailRequest;

public interface SendValidateUserEmailContract {

    void sendValidateUserEmail(EmailRequest emailRequest);
}
