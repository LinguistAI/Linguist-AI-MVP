package app.linguistai.bmvp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RMessage {
    private UUID messageId;
    private UUID conversationId;
    private String content;
    private String sender;
    private String receiver;
    private Timestamp timestamp;
}
