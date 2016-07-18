package gov.mo.dolir.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtils {

	private static Logger log = LoggerFactory.getLogger(MailUtils.class);

	public static void sendMail(String to, String from, String subject, String htmlContent, String textContent) {
		try {
			sendMail(new InternetAddress(to), new InternetAddress(from), subject, htmlContent, textContent);
		} catch (AddressException e) {
			log.error("Address Exception sending email: " + e.getMessage(), e);
		}
	}
	
	public static void sendMail(InternetAddress to, InternetAddress from, String subject, String htmlContent, String textContent) {
		Context initCtx = null;
		Context envCtx = null;
		Session session = null;
		try {
		
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			session = (Session) envCtx.lookup("mail/Session");
			
			Message message = new MimeMessage(session);
			message.setFrom(from);
			InternetAddress recipient[] = new InternetAddress[1];
			recipient[0] = to;
			message.setRecipients(Message.RecipientType.TO, recipient);
			message.setSubject(subject);
			//Unformatted text version
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(textContent, "text/plain");
			//HTML Version
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
			// Creat the multipart. Add bodyparts to it
			MimeMultipart mp = new MimeMultipart("alternative");
			mp.addBodyPart(textPart);
			mp.addBodyPart(htmlPart);
			message.setContent(mp);
			Transport.send(message);

		} catch (NamingException e) {
			log.error("Naming Exception sending email: " + e.getMessage(), e);
		} catch (AddressException e) {
			log.error("Address Exception sending email: " + e.getMessage(), e);
		} catch (MessagingException e) {
			log.error("Messaging Exception sending email: " + e.getMessage(), e);
		}
		
		
	}
	
}
