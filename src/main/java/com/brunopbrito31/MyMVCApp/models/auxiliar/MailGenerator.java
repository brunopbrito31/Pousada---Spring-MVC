package com.brunopbrito31.MyMVCApp.models.auxiliar;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailGenerator {

    public static void sendMail (
        String messageContent, 
        String subject, 
        String sourceEMail, 
        String password, 
        String rec1, 
        String rec2, 
        String rec3
    ){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
        
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication()
           {
                return new PasswordAuthentication(sourceEMail,password);
           }
        });
        
        session.setDebug(true);
    
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sourceEMail));

            Address[] toUser = InternetAddress 
                     .parse(rec1+", "+rec2+", "
                             +rec3 );

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);
            message.setText(messageContent);
        
            Transport.send(message);

            System.out.println("mail send!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // public static void sendPreConfiguredMail(){

    // }
    
}
