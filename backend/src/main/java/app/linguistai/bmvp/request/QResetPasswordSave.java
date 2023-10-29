package app.linguistai.bmvp.request;

import lombok.Data;

@Data
public class QResetPasswordSave {
	private String newPassword;
	private String email;
}
