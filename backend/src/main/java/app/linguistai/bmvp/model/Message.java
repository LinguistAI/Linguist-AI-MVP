package app.linguistai.bmvp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "message")
@NoArgsConstructor
public class Message {
    @NotNull
    @Id
    @Column(name = "message_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID messageId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", foreignKey = @ForeignKey())
    private Conversation conversation;

    @NotBlank
    @Column(name = "content", nullable = false)
    private String content;

    @NotBlank
    @Column(name = "sender", nullable = false)
    private String sender;

    @NotBlank
    @Column(name = "receiver", nullable = false)
    private String receiver;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
}
