package app.linguistai.bmvp.request;

import lombok.Data;

@Data
public class QResetPasswordSave {
	private String resetCode;
	private String newPassword;
	private String email;
}
