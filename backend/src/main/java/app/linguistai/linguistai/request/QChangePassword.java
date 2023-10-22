package app.linguistai.linguistai.request;

import lombok.Data;

@Data
public class QChangePassword {
    private String oldPassword;
    private String newPassword;
}