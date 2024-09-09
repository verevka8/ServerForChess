package com.spring.boot.serverforchess.session;

import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SessionController {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    public SessionController(SimpMessagingTemplate messagingTemplate){
     this.messagingTemplate = messagingTemplate;
    }

    public void createNewSession(String sessionId){
        Session session = new Session(sessionId,messagingTemplate);
        sessionMap.put(sessionId,session);
        new Thread(session).start();
    }
    public void receivingMessages(String sessionId, ChessMove move){
        sessionMap.get(sessionId).receiveMessage(move);
    }
    public Collection<Session> getSessions(){
        return sessionMap.values();
    }
}
