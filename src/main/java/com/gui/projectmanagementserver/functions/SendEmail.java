package com.gui.projectmanagementserver.functions;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendEmail {
    public String sendCodeToEmail(String email_receier) {
        String HOST_NAME = "smtp.gmail.com";
        int SSL_PORT = 465;
        String APP_EMAIL = "trieuunv@gmail.com";
        String APP_PASSWORD = "hwwv sztf tnyb megg";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });

        int min = 1000 ;
        int max = 9999 ;
        Random random = new Random() ;
        int code = random.nextInt(max - min + 1 ) + min ;

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_receier));
            message.setSubject("DigitalProject");
            message.setText("Mã xác thực : " + code );
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(code) ;
    }

}
