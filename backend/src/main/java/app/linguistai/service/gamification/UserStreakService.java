package app.linguistai.service.gamification;

import java.util.Calendar;
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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserStreakService {
    private final IUserStreakRepository userStreakRepository;

    @Autowired
    private final JWTUtils jwtUtils;

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
