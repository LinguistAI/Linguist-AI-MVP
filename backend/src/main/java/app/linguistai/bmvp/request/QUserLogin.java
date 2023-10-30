package app.linguistai.bmvp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QUserLogin {
	@NotNull
	private String email;

	@NotEmpty
	private String password;

	public QUserLogin(
			@JsonProperty("email") String email,
			@JsonProperty("password") String password) {

		this.email = email;
		this.password = password;
	}
}