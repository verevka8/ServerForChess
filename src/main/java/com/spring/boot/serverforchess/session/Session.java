package com.spring.boot.serverforchess.session;




import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Session implements Runnable {
    private final BlockingQueue<ChessMove> messageQueue;
    private final String sessionId;
    private final SimpMessagingTemplate messagingTemplate;

    public Session(String sessionId,SimpMessagingTemplate messagingTemplate) {
        this.messageQueue = new LinkedBlockingQueue<>();
        this.sessionId = sessionId;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run() {

        try {
            while(true) {
                ChessMove message = messageQueue.take();
                System.out.println("Session:" + message);
                sendMessage("Ok!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void receiveMessage(ChessMove message) {
        messageQueue.offer(message);
    }

    private void sendMessage(String message){
        messagingTemplate.convertAndSend("/topic/session/" + sessionId,message);
    }
}
