package dev.julioperez.api.emailNotifier.application.sendValidateUserEmail.adapter;

import dev.julioperez.api.emailNotifier.domain.model.NotificationEmail;
import dev.julioperez.api.emailNotifier.domain.port.SendValidateUserEmailOutputPort;
import dev.julioperez.api.emailNotifier.infrastructure.Gateway.SpringJavaMailer;

public class SendValidateUserEmailAdapter implements SendValidateUserEmailOutputPort {

    private final SpringJavaMailer springJavaMailer;

    public SendValidateUserEmailAdapter(SpringJavaMailer springJavaMailer) {
        this.springJavaMailer = springJavaMailer;
    }

    @Override
    public void sendValidateUserEmail(NotificationEmail notificationEmail){
        springJavaMailer.sendMail(notificationEmail);
    }
}
