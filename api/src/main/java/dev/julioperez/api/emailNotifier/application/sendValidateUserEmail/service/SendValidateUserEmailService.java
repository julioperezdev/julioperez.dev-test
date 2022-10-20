package dev.julioperez.api.emailNotifier.application.sendValidateUserEmail.service;

import dev.julioperez.api.emailNotifier.domain.exception.ErrorOccurredWhenSendingEmailException;
import dev.julioperez.api.emailNotifier.domain.model.EmailRequest;
import dev.julioperez.api.emailNotifier.domain.model.NotificationEmail;
import dev.julioperez.api.emailNotifier.domain.port.SendValidateUserEmailContract;
import dev.julioperez.api.emailNotifier.domain.port.SendValidateUserEmailOutputPort;

public class SendValidateUserEmailService implements SendValidateUserEmailContract {

    private final SendValidateUserEmailOutputPort sendValidateUserEmailOutputPort;

    public SendValidateUserEmailService(SendValidateUserEmailOutputPort sendValidateUserEmailOutputPort) {
        this.sendValidateUserEmailOutputPort = sendValidateUserEmailOutputPort;
    }


    @Override
    public void sendValidateUserEmail(EmailRequest emailRequest){
        try{
            NotificationEmail notificationEmail = new NotificationEmail(
                    "Please Activate your Account",
                    emailRequest.getEmail(),
                    String.format("Thanks you for sign up to Juliopredictor," +
                                    " please click on the below url to activate your account : " +
                                    "http://localhost:3000/verification/?token=%s",
                            emailRequest.getToken()));
            sendValidateUserEmailOutputPort.sendValidateUserEmail(notificationEmail);
        }catch (Exception exception){
            throw new ErrorOccurredWhenSendingEmailException(emailRequest.getEmail());
        }
    }
}
