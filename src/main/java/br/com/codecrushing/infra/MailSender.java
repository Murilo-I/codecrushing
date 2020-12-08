package br.com.codecrushing.infra;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MailSender {

	//@Resource(mappedName = "java:/jboss/mail/gmail")
	//private Session session;
	
	public void send(String sender, String recipient, String subject, String message) { 
//		MimeMessage mm = new MimeMessage(session);
//		try {
//			mm.setRecipients(RecipientType.TO, InternetAddress.parse(recipient));
//			mm.setFrom(new InternetAddress(sender));
//			mm.setSubject(subject);
//			mm.setContent(message, "text/html");
//			Transport.send(mm);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
	}

}
