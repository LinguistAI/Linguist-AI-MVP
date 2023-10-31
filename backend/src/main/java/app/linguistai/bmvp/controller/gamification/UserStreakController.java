package app.linguistai.bmvp.controller.gamification;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.linguistai.bmvp.exception.ExceptionLogger;
import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.response.Response;
import app.linguistai.bmvp.service.gamification.UserStreakService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user-streak")
public class UserStreakController {
    private final UserStreakService userStreakService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/all")
    public ResponseEntity<Object> getAllUserStreaks() {
        try {
            return Response.create("Successfully fetched all UserStreaks", HttpStatus.OK, userStreakService.getAllUserStreaks());
        }
        catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<Object> getUserStreakByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        try {
            return Response.create("Successfully fetched UserStreak", HttpStatus.OK, userStreakService.getUserStreakByToken(auth));
        }
        catch (NotFoundException e1) {
            return Response.create("UserStreak does not exist for user email", HttpStatus.INTERNAL_SERVER_ERROR);
        }      
        catch (Exception e2) {
            return Response.create(ExceptionLogger.log(e2), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
