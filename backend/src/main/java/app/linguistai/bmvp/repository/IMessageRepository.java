package app.linguistai.bmvp.repository;

import app.linguistai.bmvp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IMessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findAllByConversationConversationId(UUID conversationId);
}
