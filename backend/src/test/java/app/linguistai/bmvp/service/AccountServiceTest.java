package app.linguistai.bmvp.service;

import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.model.ResetToken;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.repository.IAccountRepository;
import app.linguistai.bmvp.repository.IResetTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {
	@InjectMocks
	private AccountService accountService;
	@Mock
	private IAccountRepository accountRepository;
	@Mock
	private IResetTokenRepository resetTokenRepository;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Password Reset Tests
	@DisplayName("When a non-existent user is provided for token generation, throw a NotFoundException")
	@Test
	void whenInvalidUserForResetTokenGenerationThenThrowException() {
		try {
			String email = "nonexistent@example.com";
			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.empty());

			assertThrows(NotFoundException.class, () -> accountService.generateEmailToken(email));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@DisplayName("When an existing user requests a password reset token, invalidate previous tokens of that user")
	@Test
	void whenValidUserForResetTokenGenerationThenInvalidatePreviousTokens() {
		try {
			String email = "existing@example.com";
			User user = new User();
			List<ResetToken> resetTokens = Arrays.asList(new ResetToken(), new ResetToken());

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findAllByUser(user)).thenReturn(resetTokens);

			accountService.generateEmailToken(email);

			assertTrue(resetTokens.stream().allMatch(ResetToken::isUsed));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@DisplayName("When an existing user requests a token, create and save a new token")
	@Test
	void whenValidUserForResetTokenGenerationThenCreateAndSaveNewToken() {
		try {
			String email = "existing@example.com";
			User user = new User();
			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findAllByUser(user)).thenReturn(Collections.emptyList());

			accountService.generateEmailToken(email);

			verify(resetTokenRepository, times(1)).save(any());
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@DisplayName("When a user is not found for reset token validation, throw NotFoundException")
	@Test
	void whenInvalidUserForResetTokenValidationThenThrowException() {
		try {
			String email = "nonexistent@example.com";

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.empty());

			assertThrows(NotFoundException.class, () -> accountService.validateResetCode(email, "resetCode", false));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@DisplayName("When reset token not found for validation, throw NotFoundException")
	@Test
	void whenResetTokenNotFoundForValidationThenThrowException() {
		try {
			String email = "existing@example.com";
			User user = new User();
			String resetCode = "resetCode";

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findByUserAndResetCode(user, resetCode)).thenReturn(Optional.empty());

			assertThrows(NotFoundException.class, () -> accountService.validateResetCode(email, resetCode, false));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("When reset token is expired, do not validate the token")
	void whenResetTokenExpiredThenDoNotValidate() {
		try {
			String email = "existing@example.com";
			User user = new User();
			ResetToken resetToken = new ResetToken(user);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());
			calendar.add(Calendar.MINUTE, -1);
			resetToken.setValidUntil(new Timestamp(calendar.getTime().getTime()));

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findByUserAndResetCode(user, resetToken.getResetCode())).thenReturn(Optional.of(resetToken));

			assertFalse(accountService.validateResetCode(email, resetToken.getResetCode(), false));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("When reset token is used before, do not validate")
	void whenResetTokenUsedThenDoNotValidate() {
		try {
			String email = "existing@example.com";
			User user = new User();
			ResetToken resetToken = new ResetToken(user);
			resetToken.setUsed(true);

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findByUserAndResetCode(user, resetToken.getResetCode())).thenReturn(Optional.of(resetToken));

			assertFalse(accountService.validateResetCode(email, resetToken.getResetCode(), false));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("When reset token validation is set to invalidate, invalidate the reset token")
	void whenInvalidateResetTokenTrueThenInvalidateToken() {
		try {
			String email = "existing@example.com";
			User user = new User();
			ResetToken resetToken = new ResetToken(user);

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(resetTokenRepository.findByUserAndResetCode(user, resetToken.getResetCode())).thenReturn(Optional.of(resetToken));

			boolean result = accountService.validateResetCode(email, resetToken.getResetCode(), true);

			assertTrue(result);
			assertTrue(resetToken.isUsed());
			verify(resetTokenRepository, times(1)).save(resetToken);
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("When a user is not found for setting the password after reset, throw NotFoundException")
	void whenUserNotFoundThenThrowException() {
		try {
			String email = "nonexistent@example.com";

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.empty());

			assertThrows(NotFoundException.class, () -> accountService.setPassword(email, "newPassword"));
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}

	@Test
	@DisplayName("When given an existing user email while setting the password, encode and change the user's password")
	void forValidUserChangePassword() {
		try {
			String email = "existing@example.com";
			User user = new User();
			String newPassword = "newPassword";
			String hashedPassword = "hashedPassword";

			when(accountRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
			when(bCryptPasswordEncoder.encode(newPassword)).thenReturn(hashedPassword);
			when(accountRepository.updatePassword(hashedPassword, user.getId())).thenReturn(1);

			boolean result = accountService.setPassword(email, newPassword);

			assertTrue(result);
			assertEquals(hashedPassword, user.getPassword());
		} catch (Exception e) {
			fail("TEST FAILED: " + e.getMessage());
		}
	}
}
