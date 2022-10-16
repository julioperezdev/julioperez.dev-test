package dev.julioperez.api.emailNotifier.domain.port;

import dev.julioperez.api.emailNotifier.domain.model.NotificationEmail;

public interface SendValidateUserEmailOutputPort {

    void sendValidateUserEmail(NotificationEmail notificationEmail);
}
