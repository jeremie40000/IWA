package jha.stopcovid.notification.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String mailTo, String date) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setSubject("Vous êtes déclaré cas contact - iwaStopCovid");
        simpleMailMessage.setText("Bonjour Madame/Monsieur, \n vous avez été en contact avec une personne s'étant déclaré positive sur notre application le "+date+".");
        javaMailSender.send(simpleMailMessage);
    }

}
