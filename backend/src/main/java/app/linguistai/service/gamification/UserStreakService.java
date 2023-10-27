package app.linguistai.service.gamification;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import app.linguistai.model.User;
import app.linguistai.model.UserStreak;
import app.linguistai.repository.gamification.IUserStreakRepository;
import app.linguistai.utils.DateUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserStreakService implements IUserStreakService {
    private final IUserStreakRepository userStreakRepository;

    @Override
    public UserStreak getUserStreak(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserStreak'");
    }

    @Override
    public Boolean createUserStreak(User user) {
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

    @Override
    public List<UserStreak> getAllUserStreaks() {
        try {
            return userStreakRepository.findAll();
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not fetch all UserStreaks.");
            throw e;
        }
    }
    
}
