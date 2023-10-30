package app.linguistai.bmvp.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.model.ResetToken;
import app.linguistai.bmvp.repository.IResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.repository.IAccountRepository;
import app.linguistai.bmvp.request.QChangePassword;
import app.linguistai.bmvp.request.QUserLogin;
import app.linguistai.bmvp.response.RLoginUser;
import app.linguistai.bmvp.response.RRefreshToken;
import app.linguistai.bmvp.security.JWTFilter;
import app.linguistai.bmvp.security.JWTUserService;
import app.linguistai.bmvp.security.JWTUtils;
import app.linguistai.bmvp.service.gamification.UserStreakService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
	public static int hashStrength = 10;

	@Autowired
	final BCryptPasswordEncoder bCryptPasswordEncoder;

	// @Autowired
	private final IAccountRepository accountRepository;
	private final IResetTokenRepository resetTokenRepository;
	private final JWTUserService jwtUserService;

	@Autowired
	private final JWTUtils jwtUtils;

	private final UserStreakService userStreakService;

	public RLoginUser login(QUserLogin user) throws Exception {
		try {
			User dbUser = accountRepository.findUserByEmail(user.getEmail()).orElse(null);

			if (dbUser == null) {
				throw new Exception("User is not found");
			}

			String hashedPassword = dbUser.getPassword();
			boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword);

			if (!passwordMatch) {
				throw new Exception("Passwords do not match");
			}

			System.out.println("Passwords are matched");

			final UserDetails userDetails = jwtUserService.loadUserByUsername(user.getEmail());
			final String accessToken = jwtUtils.createAccessToken(userDetails);
			final String refreshToken = jwtUtils.createRefreshToken(userDetails);

			// If login is successful, check whether to increase user streak or not
			userStreakService.updateUserStreak(dbUser.getEmail());

			return new RLoginUser(dbUser, accessToken, refreshToken);
		} catch (Exception e) {
			System.out.println("login exception");
			throw e;
		}
	}

	public RRefreshToken refreshToken(String auth) throws Exception {
		try {
			String username = jwtUtils.extractRefreshUsername(JWTFilter.getTokenWithoutBearer(auth));

			final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
			final String accessToken = jwtUtils.createAccessToken(userDetails);
			return new RRefreshToken(accessToken);

		} catch (Exception e) {
			System.out.println("refresh token exception");
			throw e;
		}
	}

	public boolean changePassword(String auth, QChangePassword passwords) throws Exception {
		try {
			String email = jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(auth));
			User dbUser = accountRepository.findUserByEmail(email).orElse(null);

			if (dbUser == null) {
				throw new Exception("User is not found");
			}

			String hashedPassword = dbUser.getPassword();

			boolean passwordMatch = bCryptPasswordEncoder.matches(passwords.getOldPassword(), hashedPassword);

			if (!passwordMatch) {
				System.out.println("passwords does not match");
				throw new Exception("pasword no match");
			}

			System.out.println("passwords are matched");
			// hash new password
			String hashedNewPassword = bCryptPasswordEncoder.encode(passwords.getNewPassword());

			dbUser.setPassword(hashedNewPassword);
			int result = accountRepository.updatePassword(hashedNewPassword, dbUser.getId());
			System.out.println("result: " + result);

			return true;
		} catch (Exception e) {
			System.out.println("password change exception exception");
			throw e;
		}
	}

	public User addUser(User user) throws Exception {
		try {
			System.out.println("user that will be saved: " + user);
			boolean userExist = accountRepository.existsByEmail(user.getEmail());

			if (userExist) {
				throw new Exception("User already exists");
			} else {
				// generate uuid and hash password if user does not exist in the system
				user.setId(UUID.randomUUID());
				user.setPassword(encodePassword(user.getPassword()));

				System.out.println("user_id: " + user.getId());
				User newUser = accountRepository.save(user);

				// Create UserStreak for the new user
				if (!userStreakService.createUserStreak(newUser)) {
					throw new Exception("ERROR: Could not generate UserStreak for user with ID: [" + newUser.getId() + "]. Perhaps UserStreak already exists?");
				}

				System.out.println("user_id after register: " + accountRepository.findUserByEmail(user.getEmail()).get().getId());
				return newUser;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public List<User> getUsers() {
		try {
			return accountRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	private String encodePassword(String plainPassword) {
		try {
			return bCryptPasswordEncoder.encode(plainPassword);
		} catch (Exception e) {
			throw e;
		}
	}

	public ResetToken generateEmailToken(String email) throws Exception {
		try {
			User user = accountRepository.findUserByEmail(email).orElse(null);
			if (user == null) {
				throw new NotFoundException("User with email [" + email + "] not found");
			}
			// invalidate previous reset tokens of user
			List<ResetToken> resetTokens = resetTokenRepository.findAllByUser(user);
			for (ResetToken resetToken : resetTokens) {
				resetToken.setUsed(true);
			}
			// create a new reset token
			ResetToken resetToken = new ResetToken(user);
			return resetTokenRepository.save(resetToken);
		} catch (Exception e) {
			System.out.println("Email token generation exception for email");
			throw e;
		}
	}

	public boolean validateResetCode(String email, String resetCode, boolean invalidate) throws Exception {
		try {
			User user = accountRepository.findUserByEmail(email).orElse(null);
			if (user == null) {
				throw new NotFoundException("User with email [" + email + "] not found");
			}

			ResetToken resetToken = resetTokenRepository.findByUserAndResetCode(user, resetCode).orElse(null);
			if (resetToken == null) {
				throw new NotFoundException("Reset token for user with email [" + user.getEmail() + "] with code [" + resetCode + "] not found.");
			}

			if (!isResetTokenValid(resetToken)) {
				return false;
			}

			if (invalidate) {
				resetToken.setUsed(true);
				resetTokenRepository.save(resetToken);
			}
			return true;

		} catch (Exception e) {
			System.out.println("Password reset token validation exception");
			throw e;
		}
	}

	private boolean isResetTokenValid(ResetToken resetToken) {
		return resetToken.getValidUntil() != null && !resetToken.getValidUntil().before(new Date()) && !resetToken.isUsed();
	}

	public boolean setPassword(String email, String password) throws Exception {
		User user = accountRepository.findUserByEmail(email).orElse(null);
		if (user == null) {
			throw new NotFoundException("User with email [" + email + "] not found");
		}
		String hashedPassword = encodePassword(password);
		user.setPassword(hashedPassword);
		int rowsAffected = accountRepository.updatePassword(hashedPassword, user.getId());
		return rowsAffected > 0;
	}

}