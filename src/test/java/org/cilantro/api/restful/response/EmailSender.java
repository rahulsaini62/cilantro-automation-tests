package org.cilantro.api.restful.response;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String toEmail, String subject, String body, String filePath) throws MessagingException, IOException {
        final String fromEmail = "rahul.saini1@appinventiv.com"; // sender's email
        final String password = "zocl sqpx pxwl vkfp"; // sender's email password

        // Set up the SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Compose the email
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(subject);

        // Create the email body
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);

        // Create the file attachment part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        File file = new File(filePath);
        if (file.exists()) {
            attachmentPart.attachFile(file);
        } else {
            System.out.println("Attachment file does not exist!");
            return;
        }

        // Create a multipart message to combine the body and the attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        // Set the content of the message
        message.setContent(multipart);

        // Send the email
        Transport.send(message);
        System.out.println("Email Sent Successfully with Attachment");
    }
}