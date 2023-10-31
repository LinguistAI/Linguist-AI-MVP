package app.linguistai.bmvp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "conversation")
@NoArgsConstructor
public class Conversation {
    @NotNull
    @Id
    @Column(name = "conversation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID conversationId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey())
    private User user;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
}
