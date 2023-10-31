package app.linguistai.bmvp.controller;

import app.linguistai.bmvp.exception.ExceptionLogger;
import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.response.Response;
import app.linguistai.bmvp.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/conversation")
public class ConversationController {
    private final ConversationService conversationService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<Object> getConversationByUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        try {
            return Response.create("Successfully fetched Conversation", HttpStatus.OK, conversationService.getConversationByToken(auth));
        }
        catch (Exception e2) {
            return Response.create(ExceptionLogger.log(e2), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
