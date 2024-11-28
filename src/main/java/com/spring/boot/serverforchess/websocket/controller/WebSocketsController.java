package com.spring.boot.serverforchess.websocket.controller;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.serverforchess.session.SessionController;
import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import com.spring.boot.serverforchess.websocket.entity.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;

import java.security.Principal;


@Controller
public class WebSocketsController {
    @Autowired
    private ObjectMapper objectMapper;
    private final SessionController sessionController;

    @Autowired
    public WebSocketsController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @MessageMapping("/game")
    public void receivingMessages(Message<MessageWrapper> message) throws JsonProcessingException {
        String userSessionId = message.getHeaders().get("simpSessionId").toString();
        String sessionId = message.getHeaders().get("nativeHeaders", LinkedMultiValueMap.class).get("sessionId").get(0).toString();
        MessageWrapper messageWrapper = message.getPayload();

//        System.out.println(messageWrapper.getJsonType());
//        System.out.println(messageWrapper.getJsonObject());

        if (messageWrapper.getJsonType().equals("ChessMove")){
            ChessMove move = objectMapper.readValue(messageWrapper.getJsonObject(),ChessMove.class);
            sessionController.receivingMessages(sessionId,userSessionId,move);
        }
        if (messageWrapper.getJsonType().equals("String")){
            sessionController.receivingMessages(sessionId,userSessionId,messageWrapper.getJsonObject());
        }
    }
}
