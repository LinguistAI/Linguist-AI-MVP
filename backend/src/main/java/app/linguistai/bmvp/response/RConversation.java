package app.linguistai.bmvp.response;

import app.linguistai.bmvp.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RConversation {
    private UUID conversationId;
    private String username;
    private String email;
    private Date date;
    private String title;
    private Boolean isNewConversation;
    private List<Message> messages;
}
