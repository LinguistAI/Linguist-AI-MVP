package app.linguistai.bmvp.service;

import app.linguistai.bmvp.model.ResetToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(String recipientEmail, String subject, String htmlText) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.setTo(recipientEmail);
		helper.setSubject(subject);
		helper.setText(htmlText, true);

		mailSender.send(message);
	}


	public void sendPasswordResetEmail(String recipientEmail, ResetToken resetToken) throws MessagingException {
		Context context = new Context();
		context.setVariable("name", resetToken.getUser().getUsername());
		context.setVariable("resetCode", resetToken.getResetCode());
		context.setVariable("timeLimitHours", resetToken.getRESET_TOKEN_TIME_LIMIT_HOURS());
		context.setVariable("sign", "The LinguistAI Team");
		String html = templateEngine.process("forgot-password", context);
		String subject = "Password reset request";
		sendEmail(recipientEmail, subject, html);
	}

}
