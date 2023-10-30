package app.linguistai.bmvp.request;

import lombok.Data;

@Data
public class QChangePassword {
	private String oldPassword;
	private String newPassword;
}