package app.linguistai.bmvp.repository;

import app.linguistai.bmvp.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IConversationRepository extends JpaRepository<Conversation, UUID> {
    Optional<Conversation> findByUserId(UUID userId);
}
