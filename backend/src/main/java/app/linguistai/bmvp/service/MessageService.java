package app.linguistai.bmvp.service;

import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.model.Conversation;
import app.linguistai.bmvp.model.Message;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.repository.IAccountRepository;
import app.linguistai.bmvp.repository.IConversationRepository;
import app.linguistai.bmvp.repository.IMessageRepository;
import app.linguistai.bmvp.request.QMessage;
import app.linguistai.bmvp.response.RMessage;
import app.linguistai.bmvp.response.RMessagePair;
import app.linguistai.bmvp.security.JWTFilter;
import app.linguistai.bmvp.security.JWTUtils;
import app.linguistai.bmvp.service.gamification.UserStreakService;
import app.linguistai.bmvp.utils.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Qualifier("no-llm-message-service")
public class MessageService implements IMessageService {
    private final UserStreakService userStreakService;

    private final IMessageRepository messageRepository;

    private final IConversationRepository conversationRepository;

    private final IAccountRepository accountRepository;

    @Autowired
    private final JWTUtils jwtUtils;

    private final String MODEL_NAME = "Model";

    @Override
    public RMessagePair sendMessage(QMessage qMessage, String token) throws Exception {
        try {
            String email = jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(token));
            Optional<User> optionalUser = accountRepository.findUserByEmail(email);

            if (optionalUser.isEmpty()) {
                throw new NotFoundException("User does not exist for given email: [" + email + "].");
            }

            User senderUser = optionalUser.get();

            Optional<Conversation> optionalConversation = conversationRepository.findById(qMessage.getConversationId());

            if (optionalConversation.isEmpty()) {
                throw new NotFoundException("Conversation does not exist for given conversationId: [" + qMessage.getConversationId() + "].");
            }

            Message message = new Message();
            message.setConversation(optionalConversation.get());
            message.setContent(qMessage.getContent());
            message.setSender(senderUser.getUsername());
            message.setReceiver(MODEL_NAME);
            message.setTimestamp(Timestamp.from(Instant.now()));

            Message savedMessage = messageRepository.save(message);

            // replyMessage = reply of LLM
            RMessage replyMessage = reply(savedMessage);

            // If user sent a message and the model replied successfully, check whether to increase user streak or not
            userStreakService.updateUserStreak(senderUser.getEmail());

            return MessageMapper.toRMessagePair(MessageMapper.toRMessage(savedMessage), replyMessage);
        }
        catch (NotFoundException e1) {
            throw e1;
        }
        catch (Exception e2) {
            System.out.println("ERROR: Could not send message.");
            throw e2;
        }
    }

    /**
     *  Since LLM is not connected yet, this method returns dummy responses
     */
    @Override
    public RMessage reply(Message message) throws Exception {
        try {
            String responseLLM = "Hi " + message.getSender() + "!, I am " + message.getReceiver() + ". Nice to meet you.";

            Message replyMessage = new Message();
            replyMessage.setConversation(message.getConversation());
            replyMessage.setContent(responseLLM);
            replyMessage.setSender(MODEL_NAME);
            replyMessage.setReceiver(message.getSender());
            replyMessage.setTimestamp(Timestamp.from(Instant.now()));

            Message savedReplyMessage = messageRepository.save(replyMessage);

            return MessageMapper.toRMessage(savedReplyMessage);
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not reply to message.");
            throw e;
        }
    }
}
