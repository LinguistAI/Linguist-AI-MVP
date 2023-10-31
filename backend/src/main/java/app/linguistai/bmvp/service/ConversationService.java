package app.linguistai.bmvp.service;

import app.linguistai.bmvp.exception.NotFoundException;
import app.linguistai.bmvp.model.Conversation;
import app.linguistai.bmvp.model.User;
import app.linguistai.bmvp.repository.IAccountRepository;
import app.linguistai.bmvp.repository.IConversationRepository;
import app.linguistai.bmvp.repository.IMessageRepository;
import app.linguistai.bmvp.response.RConversation;
import app.linguistai.bmvp.security.JWTFilter;
import app.linguistai.bmvp.security.JWTUtils;
import app.linguistai.bmvp.utils.DateUtils;
import app.linguistai.bmvp.utils.mapper.ConversationMapper;
import app.linguistai.bmvp.utils.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConversationService {
    private final IConversationRepository conversationRepository;

    private final IMessageRepository messageRepository;

    private final IAccountRepository accountRepository;

    @Autowired
    private final JWTUtils jwtUtils;

    public RConversation getConversationByToken(String token) throws Exception {
        try {
            String email = jwtUtils.extractAccessUsername(JWTFilter.getTokenWithoutBearer(token));
            return getConversationByUserEmail(email);
        }
        catch (NotFoundException e1) {
            throw e1;
        }
        catch (Exception e2) {
            System.out.println("ERROR: Could not fetch Conversation.");
            throw e2;
        }
    }

    private RConversation getConversationByUserEmail(String email) throws Exception {
        try {
            Optional<User> optionalUser = accountRepository.findUserByEmail(email);

            if (optionalUser.isEmpty()) {
                throw new NotFoundException("User does not exist for given email: [" + email + "].");
            }

            Optional<Conversation> optionalConversation = conversationRepository.findByUserId(optionalUser.get().getId());

            if (optionalConversation.isEmpty()) {
                return createNewConversationForEmail(email);
            }

            Conversation conversation = optionalConversation.get();
            return ConversationMapper.toRConversation(
                    conversation,
                    Boolean.FALSE,
                    MessageMapper.toRMessageList(
                            messageRepository.findAllByConversationConversationIdOrderByTimestampDesc(conversation.getConversationId())
                    )
            );
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not fetch Conversation.");
            throw e;
        }
    }

    private RConversation createNewConversationForEmail(String email) throws Exception {
        try {
            Optional<User> optionalUser = accountRepository.findUserByEmail(email);

            if (optionalUser.isEmpty()) {
                throw new NotFoundException("User does not exist for given email: [" + email + "].");
            }

            Conversation newConversation = new Conversation();
            newConversation.setUser(optionalUser.get());
            newConversation.setDate(DateUtils.convertUtilDateToSqlDate(Calendar.getInstance().getTime()));
            newConversation.setTitle("Conversation");

            return ConversationMapper.toRConversation(conversationRepository.save(newConversation), Boolean.TRUE, Collections.emptyList());
        }
        catch (Exception e) {
            System.out.println("ERROR: Could not create Conversation.");
            throw e;
        }
    }
}
