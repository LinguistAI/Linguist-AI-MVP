package app.linguistai.bmvp.response;

import java.util.UUID;

import app.linguistai.bmvp.model.User;
import jakarta.persistence.Id;

import lombok.Data;

@Data
public class RLoginUser {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;

    public RLoginUser(User user, String accessToken, String refreshToken) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
