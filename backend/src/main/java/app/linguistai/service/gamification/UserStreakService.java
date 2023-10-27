package app.linguistai.service.gamification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.linguistai.exception.NotFoundException;
import app.linguistai.model.User;
import app.linguistai.model.UserStreak;
import app.linguistai.repository.gamification.IUserStreakRepository;
import app.linguistai.security.JWTFilter;
import app.linguistai.security.JWTUtils;
import app.linguistai.utils.DateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserStreakService {
    private final IUserStreakRepository userStreakRepository;

    @Autowired
    private final JWTUtils jwtUtils;

    public UserStreak checkUserStreakForUpdate(UserStreak userStreak) throws Exception {
        try {
            Date streakTime = DateUtils.convertSqlDateToUtilDate(userStreak.getLastLogin());
            
            if (streakTime == null) {
                throw new Exception("StreakTime is null");
            }
            
            LocalDate lastLoginLocalDate = streakTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            long daysDifference = ChronoUnit.DAYS.between(lastLoginLocalDate, currentDate);
            
            // Streak must be incremented
            if (daysDifference == 1) {
                return incrementUserStreak(userStreak);
            }
            // Streak must be reset || (edge case, if last login is after current time) Streak must be reset
            else if (daysDifference > 1 || daysDifference < 1) {
                return resetUserStreak(userStreak);
            }
            // else if (daysDifference == 0), Do nothing. Maybe later add hello again message?
            return userStreak;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    private UserStreak incrementUserStreak(UserStreak userStreak) throws Exception {
        try {
            // Assume user streak increment operation is correct
            UserStreak newUserStreak = new UserStreak();
            newUserStreak.setCurrentStreak(userStreak.getCurrentStreak() + 1);
            newUserStreak.setHighestStreak(
                userStreak.getHighestStreak() <= userStreak.getCurrentStreak()
                ? userStreak.getHighestStreak() + 1
                : userStreak.getHighestStreak()
            );
            newUserStreak.setLastLogin(DateUtils.convertUtilDateToSqlDate(Calendar.getInstance().getTime()));
            newUserStreak.setUserId(userStreak.getUserId());

            userStreakRepository.deleteById(userStreak.getUserId());
            return userStreakRepository.save(newUserStreak);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    private UserStreak resetUserStreak(UserStreak userStreak) throws Exception {
        try {
            // Assume user streak reset operation is correct
            UserStreak newUserStreak = new UserStreak();
            newUserStreak.setCurrentStreak(1);
            newUserStreak.setHighestStreak(userStreak.getHighestStreak());
            newUserStreak.setLastLogin(DateUtils.convertUtilDateToSqlDate(Calendar.getInstance().getTime()));
            newUserStreak.setUserId(userStreak.getUserId());

            userStreakRepository.deleteById(userStreak.getUserId());
            return userStreakRepository.save(newUserStreak);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public UserStreak getUserStreak(String auth) throws Exception {
        try {
            String email = jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(auth));
            Optional<UserStreak> optionalStreak = userStreakRepository.findByUserEmail(email);

            if (!optionalStreak.isPresent()) {
                throw new NotFoundException("UserStreak does not exist for given email: [" + email + "].");
            }

            return optionalStreak.get();
        }
        catch (NotFoundException e1) {
            throw e1;
        }
        catch (Exception e2) {
            System.out.println("ERROR: Could not fetch UserStreak.");
            throw e2;
        }
    }

    public Boolean createUserStreak(User user) throws Exception {
        try {
            if (userStreakRepository.findByUserEmail(user.getEmail()).isPresent()) {
                return false; // UserStreak already exists
            }
            
            // Otherwise, create new "blank" UserStreak
            UserStreak newUserStreak = new UserStreak();
            newUserStreak.setCurrentStreak(1);
            newUserStreak.setHighestStreak(1);
            newUserStreak.setLastLogin(DateUtils.convertUtilDateToSqlDate(Calendar.getInstance().getTime()));
            newUserStreak.setUserId(user.getId());

            userStreakRepository.save(newUserStreak);
            return true;
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not create UserStreak.");
            throw e;
        }
    }

    public List<UserStreak> getAllUserStreaks() throws Exception {
        try {
            return userStreakRepository.findAll();
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not fetch all UserStreaks.");
            throw e;
        }
    }
    
}
