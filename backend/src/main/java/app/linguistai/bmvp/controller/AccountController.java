package app.linguistai.bmvp.controller;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.linguistai.bmvp.exception.ExceptionLogger;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.request.QChangePassword;
import app.linguistai.bmvp.request.QUserLogin;
import app.linguistai.bmvp.response.RLoginUser;
import app.linguistai.bmvp.response.RRefreshToken;
import app.linguistai.bmvp.response.Response;
import app.linguistai.bmvp.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AccountController {
    private final AccountService accountService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody QUserLogin userInfo) {
        try {
            RLoginUser token = accountService.login(userInfo);
            return Response.create("login is successful", HttpStatus.OK, token);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), 499);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register")
    public ResponseEntity<Object> register(@Valid @RequestBody User userInfo) {
        try {
            User ids = accountService.addUser(userInfo);
            return Response.create("account is created", HttpStatus.OK, ids);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.CONFLICT);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/change_password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody QChangePassword userInfo,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {

        try {
            accountService.changePassword(auth, userInfo);
            return Response.create("password is changed", HttpStatus.OK);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.BAD_REQUEST);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        try {
            RRefreshToken newToken = accountService.refreshToken(auth);
            return Response.create("new accsess token is created", HttpStatus.OK, newToken);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.BAD_REQUEST);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/test")
    public ResponseEntity<Object> testAuth() {
        try {
            String test = "welcome to the authenticated endpoint!";
            return Response.create("ok", HttpStatus.OK, test);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.BAD_REQUEST);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/")
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> userList = accountService.getUsers();
            return Response.create("ok", HttpStatus.OK, userList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);  
        }        
    }
}
