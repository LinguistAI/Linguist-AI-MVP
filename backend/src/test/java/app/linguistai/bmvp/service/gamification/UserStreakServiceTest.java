package app.linguistai.bmvp.service.gamification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.repository.gamification.IUserStreakRepository;

@SpringBootTest
public class UserStreakServiceTest {
    @InjectMocks
    private UserStreakService userStreakService;

    @Mock
    private IUserStreakRepository userStreakRepository;

    @DisplayName("When given a valid new User, create the relevant UserStreak")
    @Test
    void WhenValidNewUserThenCreateUserStreak() {
        try {
            User user = new User();
            user.setEmail("test@test.test");
            user.setId(UUID.randomUUID());
            user.setPassword("pw");
            user.setUsername("test");

            when(userStreakRepository.findByUserEmail(user.getEmail())).thenReturn(Optional.empty());

            assertEquals(true, userStreakService.createUserStreak(user));
            verify(userStreakRepository, times(1)).save(any());
        }
        catch (Exception e) {
            fail("TEST FAILED: " + e.getMessage());
        }
    }
}
