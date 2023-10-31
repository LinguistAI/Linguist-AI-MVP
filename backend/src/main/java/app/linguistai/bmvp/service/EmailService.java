package app.linguistai.bmvp.service;

import app.linguistai.bmvp.model.ResetToken;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


@Service
public class EmailService {
	private final String LOGO_ATTACHMENT_FILE_NAME = "LinguistAI Logo"; // cosmetic
	private final String LOGO_PATH = "Logo.png";
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(String recipientEmail, String subject, String htmlText) throws Exception {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setTo(recipientEmail);
			helper.setSubject(subject);
			helper.setText(htmlText, true);

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("ERROR: Could not send email.");
			throw e;
		}
	}

	public void sendEmail(String recipientEmail, String subject, String htmlText, MultipartFile image, String attachmentName) throws Exception {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setTo(recipientEmail);
			helper.setSubject(subject);
			helper.setText(htmlText, true);

			final InputStreamSource imageSource = new ByteArrayResource(image.getBytes());
			helper.addInline(image.getName(), imageSource, Objects.requireNonNull(image.getContentType()));
			// Inline image is shown also as an attachment in certain email clients. In that case, change the file name
			helper.getMimeMultipart().getBodyPart("<" + image.getName() + ">").setFileName(attachmentName);

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("ERROR: Could not send email with inline image.");
			throw e;
		}

	}

	public void sendPasswordResetEmail(String recipientEmail, ResetToken resetToken) throws Exception {
		try {
			Context context = new Context();
			context.setVariable("name", resetToken.getUser().getUsername());
			context.setVariable("resetCode", resetToken.getResetCode());
			context.setVariable("timeLimitMinutes", resetToken.getTimeLimitInMinutes());
			context.setVariable("sign", "The LinguistAI Team");

			MultipartFile image = convertImage(LOGO_PATH);
			context.setVariable("imageResourceName", image.getName());

			String htmlText = templateEngine.process("forgot-password", context);
			String subject = "Password reset request";
			sendEmail(recipientEmail, subject, htmlText, image, LOGO_ATTACHMENT_FILE_NAME);
		} catch (Exception e) {
			System.out.println("ERROR: Could not send password reset email.");
			throw e;
		}
	}

	private MultipartFile convertImage(String imagePath) throws Exception{
		try {
			Resource resource = new ClassPathResource(imagePath);
			byte[] imageBytes = resource.getInputStream().readAllBytes();
			String[] parts = imagePath.split("\\.");
			String extension;
			if (parts.length > 1) {
				extension = parts[parts.length - 1].toLowerCase();
			}
			else{
				throw new Exception("ERROR: Could not determine the image extension.");
			}
			return new MockMultipartFile(
					imagePath, imagePath, "image/" + extension, imageBytes);
		} catch (Exception e) {
			System.out.println("ERROR: Could not convert the image into a MultipartFile.");
			throw new Exception(e);
		}
	}

}
