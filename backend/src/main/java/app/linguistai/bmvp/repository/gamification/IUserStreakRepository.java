package app.linguistai.bmvp.repository.gamification;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.linguistai.bmvp.model.UserStreak;

@Repository
public interface IUserStreakRepository extends JpaRepository<UserStreak, UUID> {
	List<UserStreak> findAll();

	Optional<UserStreak> findByUserId(UUID id);

	Optional<UserStreak> findByUserEmail(String email);
}