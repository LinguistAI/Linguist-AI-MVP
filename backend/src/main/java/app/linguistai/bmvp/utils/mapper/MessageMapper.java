package app.linguistai.bmvp.utils.mapper;

import app.linguistai.bmvp.model.Message;
import app.linguistai.bmvp.response.RMessage;
import app.linguistai.bmvp.response.RMessagePair;

public class MessageMapper {
    public static RMessage toRMessage(Message message) {
        return new RMessage(
            message.getMessageId(),
            message.getConversation().getConversationId(),
            message.getContent(),
            message.getSender(),
            message.getReceiver(),
            message.getTimestamp()
        );
    }

    public static RMessagePair toRMessagePair(RMessage userMessage, RMessage replyMessage) {
        return new RMessagePair(userMessage, replyMessage);
    }
}
