package au.gov.nsw.records.digitalarchives.dashboard.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private transient MailSender mailTemplate;

    @Autowired
    private transient SimpleMailMessage mailMessage;

    public void sendMessage(String mailTo, String message) {
         
        mailMessage.setTo(mailTo);
        mailMessage.setText(message);
        mailTemplate.send(mailMessage);
        
    }
}
