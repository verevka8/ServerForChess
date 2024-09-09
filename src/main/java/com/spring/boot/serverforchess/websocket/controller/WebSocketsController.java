package com.spring.boot.serverforchess.websocket.controller;





import com.spring.boot.serverforchess.session.SessionController;
import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;




@Controller
public class WebSocketsController {
    private final SessionController sessionController;

    @Autowired
    public WebSocketsController(SessionController sessionController){
        this.sessionController = sessionController;
    }

    @MessageMapping("/game")
    public void receivingMessages(Message<ChessMove> message){
        System.out.println(message.getHeaders());
        String sessionId = message.getHeaders().get("nativeHeaders", LinkedMultiValueMap.class).get("sessionId").get(0).toString();
        sessionController.receivingMessages(sessionId,message.getPayload());

    }
}
