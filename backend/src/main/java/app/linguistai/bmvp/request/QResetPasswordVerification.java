package app.linguistai.bmvp.request;

import lombok.Data;

@Data
public class QResetPasswordVerification {
	private String resetCode;
	private String email;
}
