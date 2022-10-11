package com.reader.purchase;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmailService {

	public static final String SENDER_MAIL_ID = "chinnareddy13318@gmail.com";
	public static final String SENDER_MAIL_PASSWORD = "cjgbvrbrmekxurum";

	public void send(List<String> toMailIds, String subject, String content) {
		sendEmail(toMailIds, subject, content);
	}

	private void sendEmail(List<String> toMailIds, String subject, String content) {
		String host = "smtp.gamil.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth.", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_MAIL_ID, SENDER_MAIL_PASSWORD);
			}
		});
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(SENDER_MAIL_ID);
			message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toMailIds.toString()));
			message.setText(content);
			message.setSubject(subject);

			Transport.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}