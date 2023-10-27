package app.linguistai.service.gamification;

import java.util.List;

import app.linguistai.model.User;
import app.linguistai.model.UserStreak;

public interface IUserStreakService {
    public UserStreak getUserStreak(User user);
    public Boolean createUserStreak(User user);
    public List<UserStreak> getAllUserStreaks();
}
