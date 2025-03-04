package org.cilantro.utils;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.BodyPart;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.ContentType;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;

public final class MailUtil {
    private final static String host          = "imap.gmail.com";
    private final static String mailStoreType = "imaps";
    private final static String port          = "993";
    private static       String email;
    private static       String password;

    public static String getOtp () {
        try {
            String otp = "";
            // delay to let backend sends email with OTP
            Thread.sleep (1000);
            final Properties properties = new Properties ();
            properties.put ("mail.imap.host", host);
            properties.put ("mail.imap.port", port);
            properties.put ("mail.imap.starttls.enable", "true");
            properties.put ("mail.imap.ssl.trust", host);
            final Session emailSession = Session.getDefaultInstance (properties);
            final Store store = emailSession.getStore (mailStoreType);
            email = PropertiesUtil.getApplicationProps ("gmailUsername");
            password = PropertiesUtil.getApplicationProps ("gmailAppPassword");
            store.connect (host, email, password);
            final Folder inbox = store.getFolder ("Inbox");
            inbox.open (Folder.READ_WRITE);
            // all unread emails from Inbox folder is to be in the messages array
            final Message[] messages = inbox.search (new FlagTerm (new Flags (Flags.Flag.SEEN), false));
            // go through all the messages
            for (final Message singleMessage : messages) {
                // find a message with the same Subject as emails with OTP usually have
                if (singleMessage.getSubject ()
                    .equals (PropertiesUtil.getApplicationProps ("otpMailSubject"))) {
                    singleMessage.setFlag (Flags.Flag.SEEN, true);
                    final String messageBody = getMessageBody (singleMessage);
                    final String otpPhrase = PropertiesUtil.getApplicationProps ("otpMailPhrase");
                    // find index inside the message body where OTP is written
                    final int indexOfOtpStart = messageBody.indexOf (otpPhrase) + otpPhrase.length ();
                    // get 6-digit OTP
                    otp = otp + messageBody.substring (indexOfOtpStart, indexOfOtpStart + 6);
                }
            }
            inbox.close (false);
            store.close ();
            return otp;
        } catch (final Exception e) {
            throw new RuntimeException ("There are problems with reading emails.");
        }
    }

    private static String getMessageBody (final Message message) throws MessagingException, IOException {
        String messageBody = "";
        if (message.isMimeType ("text/plain")) {
            messageBody = message.getContent ()
                .toString ();
        } else if (message.isMimeType ("multipart/*")) {
            final MimeMultipart mimeMultipart = (MimeMultipart) message.getContent ();
            messageBody = getTextFromMimeMultipart (mimeMultipart);
        }
        return messageBody;
    }

    private static String getTextFromMimeMultipart (final MimeMultipart mimeMultipart)
        throws IOException, MessagingException {
        final int count = mimeMultipart.getCount ();
        if (count == 0)
            throw new MessagingException ("Multipart with no body parts not supported.");
        final boolean multipartAlt = new ContentType (mimeMultipart.getContentType ()).match ("multipart/alternative");
        if (multipartAlt)
            return getTextFromBodyPart (mimeMultipart.getBodyPart (count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            final BodyPart bodyPart = mimeMultipart.getBodyPart (i);
            result += getTextFromBodyPart (bodyPart);
        }
        return result;
    }

    private static String getTextFromBodyPart (final BodyPart bodyPart) throws IOException, MessagingException {
        String result = "";
        if (bodyPart.isMimeType ("text/plain")) {
            result = (String) bodyPart.getContent ();
        } else if (bodyPart.isMimeType ("text/html")) {
            String html = (String) bodyPart.getContent ();
            result = org.jsoup.Jsoup.parse (html)
                .text ();
        } else if (bodyPart.getContent () instanceof MimeMultipart) {
            result = getTextFromMimeMultipart ((MimeMultipart) bodyPart.getContent ());
        }
        return result;
    }

    public static void main (final String[] args) {
        System.out.println (MailUtil.getOtp ());
    }
}
