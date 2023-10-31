package app.linguistai.bmvp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class QMessage {
    @NotNull
    private UUID conversationId;

    @NotBlank
    private String content;

    @NotBlank
    private String sender;

    public QMessage(
            @JsonProperty("conversationId") UUID conversationId,
            @JsonProperty("content") String content,
            @JsonProperty("sender") String sender
    ) {
        this.conversationId = conversationId;
        this.content = content;
        this.sender = sender;
    }
}
