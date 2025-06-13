package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendTestReport(String reportPath) {
		final String sendersEmail = "yznagle@gmail.com";
		final String appPassword = "uocebdeafiahubco";
		final String recipientEmail = "yznagle@gmail.com";

//		SMTP Server Configurations

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");
		
//		Create a session with Authentication

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sendersEmail, appPassword);
			}
		});
		session.setDebug(true);

		try {

//			Create Email Message

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendersEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Email of Test Automation");
//			message.setText("Hello \nThis is a test email from Java Selenium Automation Code\n\n\nRegards,\n\nQA Team");

//			Email Body 

			MimeBodyPart emailBody = new MimeBodyPart();
			emailBody.setText(
					"Hello \\nThis email is sent by Java Selenium Automation Code\\n\\n\\nRegards,\\n\\nQA Team");

//			Email Attachment

			MimeBodyPart emailAttachment = new MimeBodyPart();
//			String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			System.out.println("Attachment fie is in following folder: " + reportPath);
			emailAttachment.attachFile(new File(reportPath));

//			Combine Email Body & Attachment

			MimeMultipart completeMail = new MimeMultipart();
			completeMail.addBodyPart(emailBody);
			completeMail.addBodyPart(emailAttachment);
			message.setContent(completeMail);

//			Send Email Message
			Transport.send(message);
			System.out.println("Email Sent Successfully...!!!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
