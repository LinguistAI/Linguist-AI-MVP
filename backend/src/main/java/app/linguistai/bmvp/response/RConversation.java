package app.linguistai.bmvp.response;

import app.linguistai.bmvp.model.Conversation;
import app.linguistai.bmvp.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RConversation {
    private Conversation conversation;
    private Boolean isNewConversation;
    private List<Message> messages;
}
