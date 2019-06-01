package br.com.kerubin.api.user.account.mail;

import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.kerubin.api.user.account.exception.UserAccountException;

@Component
public class UserAccountMailer {
	
	private static final Logger log = LoggerFactory.getLogger(UserAccountMailer.class);
	
	@Inject
	private JavaMailSender mailSender;
	
	public void sendMail(String from, List<String> recipients, String subsject, String message) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			try {
				helper.setFrom(from);
				helper.setTo(recipients.toArray(new String[recipients.size()]));
				helper.setSubject(subsject);
				helper.setText(message, true);
				log.info("Sending e-mail to: " + recipients);
				mailSender.send(mimeMessage);
				log.info("E-mail sended to: " + recipients);
			} catch (Exception e) {
				log.error("Error sending e-mail to : " + recipients, e);
				new UserAccountException("Error sending e-mail.");
			}
			
	}

}
