package app.linguistai.bmvp.service.gamification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.model.UserStreak;
import app.linguistai.bmvp.repository.gamification.IUserStreakRepository;
import app.linguistai.bmvp.security.JWTUtils;

@SpringBootTest
public class UserStreakServiceTest {
    @InjectMocks
    private UserStreakService userStreakService;

    @Mock
    private IUserStreakRepository userStreakRepository;

    @Mock
    private JWTUtils jwtUtils;

    @DisplayName("When given a valid new User, create the relevant UserStreak")
    @Test
    void whenValidNewUserThenCreateUserStreak() {
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

    @DisplayName("When updating UserStreak for a user with no existing streak, throw a NotFoundException")
    @Test
    void whenUserHasNoUserStreakForUpdateUserStreakThenThrowNotFoundException() {
        String email = "test@test.test";

        when(userStreakRepository.findByUserEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userStreakService.updateUserStreak(email));
    }

    @DisplayName("When given an null input while fetching UserStreak, throw an exception")
    @Test
    void whenGivenNullInputForGetUserStreakThenThrowException() {
        try {
            String input = null;
            assertThrows(Exception.class, () -> userStreakService.getUserStreak(input));
        }
        catch (Exception e) {
            fail("TEST FAILED: " + e.getMessage());
        }
    }

    @DisplayName("When all UserStreaks are fetched, then return all UserStreaks successfully")
    @Test
    void whenFetchedAllUserStreaksThenReturnAllUserStreaks() {
        try {
            List<UserStreak> userStreaks = Arrays.asList(new UserStreak(), new UserStreak());

            when(userStreakRepository.findAll()).thenReturn(userStreaks);

            assertEquals(userStreaks.size(), userStreakService.getAllUserStreaks().size());
        }
        catch (Exception e) {
            fail("TEST FAILED: " + e.getMessage());
        }
    }
}
