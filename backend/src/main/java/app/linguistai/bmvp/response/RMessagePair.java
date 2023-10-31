package app.linguistai.bmvp.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RMessagePair {
    private RMessage userMessage;
    private RMessage replyMessage;
}
