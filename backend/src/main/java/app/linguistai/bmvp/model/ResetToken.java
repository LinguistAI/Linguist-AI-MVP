package app.linguistai.bmvp.model;

import app.linguistai.bmvp.utils.DateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Date;
import java.util.Calendar;

@Data
@Entity
@Table(name = "reset_token")
@NoArgsConstructor
public class ResetToken {
	private final int RESET_TOKEN_LENGTH = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey())
	private User user;

	@Column(name = "reset_code", nullable = false)
	private String resetCode;

	@Column(name = "is_used", nullable = false)
	private boolean isUsed;

	@Column(name = "valid_until", nullable = false)
	private Date validUntil;

	public ResetToken(User user) {
		this.user = user;
		this.resetCode = RandomStringUtils.randomAlphanumeric(6);
		this.isUsed = false;
		this.validUntil = DateUtils.convertUtilDateToSqlDate(DateUtils.addHours(Calendar.getInstance().getTime(), 12));
	}
}
