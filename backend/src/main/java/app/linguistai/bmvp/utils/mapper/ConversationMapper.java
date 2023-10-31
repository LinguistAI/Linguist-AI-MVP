package app.linguistai.bmvp.utils.mapper;

import app.linguistai.bmvp.model.Conversation;
import app.linguistai.bmvp.response.RConversation;
import app.linguistai.bmvp.response.RMessage;

import java.util.List;

public class ConversationMapper {
    public static RConversation toRConversation(Conversation conversation, Boolean isNewConversation, List<RMessage> messages) {
        return new RConversation(
            conversation.getConversationId(),
            conversation.getUser().getUsername(),
            conversation.getUser().getEmail(),
            conversation.getDate(),
            conversation.getTitle(),
            isNewConversation,
            messages
        );
    }
}
