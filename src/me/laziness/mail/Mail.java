package me.laziness.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private Session session;
	private String userName = "1234wngml";

	public static Mail getInstance() { return Singleton.INSTANCE; }
	private static class Singleton { private static final Mail INSTANCE = new Mail(); }
	private Mail() {
		String password = "anju165!";
		Properties props = new Properties();
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication
					getPasswordAuthentication() { return new PasswordAuthentication(userName, password); }
		});
	} //Mail();
	
	public void sendEmail(String to, String subject, String content)  {
		new Thread(() -> {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(userName));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
				message.setSubject(subject);
				message.setText(content);
				message.setSentDate(new Date());
				Transport.send(message);
			}catch (MessagingException e) { e.printStackTrace(); }
		}).start();
	} //sendMail();
	
} //class Mail;
