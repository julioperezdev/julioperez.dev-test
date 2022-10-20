package dev.julioperez.api.emailNotifier.infrastructure.Gateway;

import dev.julioperez.api.emailNotifier.domain.exception.ErrorOccurredWhenSendingEmailException;
import dev.julioperez.api.emailNotifier.domain.model.NotificationEmail;
import dev.julioperez.api.shared.application.getProperty.service.GetPropertyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SpringJavaMailer {
    private final JavaMailSender mailSender;

    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(GetPropertyService.getPropertyByKey("julioperez.mail.description.from"));
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
            //messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new ErrorOccurredWhenSendingEmailException(notificationEmail.getRecipient());
        }
    }
}