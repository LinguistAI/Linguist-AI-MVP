package app.linguistai.controller.gamification;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.linguistai.exception.ExceptionLogger;
import app.linguistai.request.QUserLogin;
import app.linguistai.response.RLoginUser;
import app.linguistai.response.Response;
import app.linguistai.service.AccountService;
import app.linguistai.service.gamification.IUserStreakService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user-streak")
public class UserStreakController {
    private final IUserStreakService userStreakService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<Object> login() {
        try {
            return Response.create("Successfully fetched all UserStreaks", HttpStatus.OK, userStreakService.getAllUserStreaks());
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
