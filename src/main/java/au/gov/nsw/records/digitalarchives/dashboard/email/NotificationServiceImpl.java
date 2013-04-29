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
    private transient SimpleMailMessage templateMessage;

    public void sendMessage(String mailTo, String message) {
        org.springframework.mail.SimpleMailMessage mailMessage = new org.springframework.mail.SimpleMailMessage(templateMessage);
        /*
        HtmlEmail email = new HtmlEmail();

        email.setHostName(config.getMailHost());
  			if (!config.getMailPassword().isEmpty()){
  				email.setAuthentication(config.getMailUser(), config.getMailPassword());
  			}
  			email.setSmtpPort(config.getMailPort());
  			email.setFrom(config.getMailUser());
  			for (String to: recipients){
  				email.addTo(to);
  			}
  			email.setSubject(String.format("[%d - %s] %s", cache.getLongId(), cache.getString(WorkflowCache.NAME), subject));

  			if (includeLastError){
  				message = message + "\n\nError:\n" + cache.getString(WorkflowCache.LASTERROR);
  			}
  			email.setTextMsg(message);
  			//email.setHtmlMsg(htmlBody);

  			email.setDebug(false);
  			email.send();
  			*/
//        mailMessage.setTo(mailTo);
//        mailMessage.setText(message);
//        mailTemplate.send(mailMessage);
    }
}
