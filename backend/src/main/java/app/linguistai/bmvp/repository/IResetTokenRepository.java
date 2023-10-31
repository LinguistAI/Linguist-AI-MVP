package app.linguistai.bmvp.repository;

import app.linguistai.bmvp.model.ResetToken;
import app.linguistai.bmvp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IResetTokenRepository extends JpaRepository<ResetToken, UUID> {
	List<ResetToken> findAllByUser(User user);
	Optional<ResetToken> findByUserAndResetCode(User user, String resetCode);
}
