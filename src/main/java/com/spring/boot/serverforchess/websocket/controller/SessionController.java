package com.spring.boot.serverforchess.websocket.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.boot.serverforchess.session.Session;
import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@Controller
public class SessionController {
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SessionController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/game")
    public void receivingMessages(Message<ChessMove> message){
        System.out.println(message.getHeaders());
        String sessionId = message.getHeaders().get("nativeHeaders", LinkedMultiValueMap.class).get("sessionId").get(0).toString();
        sessionMap.get(sessionId).receiveMessage(message.getPayload());
    }

    public void createNewSession(String sessionId){
        Session session = new Session(sessionId,messagingTemplate);
        sessionMap.put(sessionId,session);
        new Thread(session).start();
    }
    // TODO: Вынести управление сессиями в другое место
}
