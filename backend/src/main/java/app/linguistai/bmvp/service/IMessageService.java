package app.linguistai.bmvp.service;

import app.linguistai.bmvp.model.Message;
import app.linguistai.bmvp.request.QMessage;
import app.linguistai.bmvp.response.RMessage;
import app.linguistai.bmvp.response.RMessagePair;

public interface IMessageService {
    public RMessagePair sendMessage(QMessage qMessage, String token) throws Exception;
    public RMessage reply(Message message) throws Exception;
}
