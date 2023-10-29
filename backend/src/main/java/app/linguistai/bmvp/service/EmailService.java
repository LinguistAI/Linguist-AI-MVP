package app.linguistai.bmvp.service;

import app.linguistai.bmvp.model.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	private boolean sendEmail(String recipientEmail, String subject, String text) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(recipientEmail);
			mailMessage.setSubject(subject);
			mailMessage.setText(text);
			javaMailSender.send(mailMessage);
			return true;
		} catch (Exception e) {
			System.out.println("Email sending exception");
			throw e;
		}
	}

	public boolean sendPasswordResetEmail(String recipientEmail, ResetToken resetToken) {
		String subject = "Password reset request";
		String text = String.format("You have requested a password reset. Please enter the following code in your app: \n %s", resetToken.getResetCode());
		return sendEmail(recipientEmail, subject, text);
	}
}
