package app.linguistai.model;

import java.util.UUID;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_streak")
@NoArgsConstructor
public class UserStreak {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey())
    private User user;

    @NotNull
    @Column(name = "current_streak", nullable = false)
    private Integer currentStreak;

    @NotNull
    @Column(name = "highest_streak", nullable = false)
    private Integer highestStreak;

    @NotNull
    @Column(name = "last_login", nullable = false)
    private Date lastLogin;
}
