package com.spring.boot.serverforchess.session;




import com.spring.boot.serverforchess.websocket.entity.ChessMove;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Session implements Runnable {
    private final BlockingQueue<InformationOfChessMove> messageQueue;
    private final SimpMessagingTemplate messagingTemplate;
    private boolean startGame = false;
    public final String sessionId;

    private List<String> immutableList;
    private  List<String> tempList = new ArrayList<>();



    public Session(String sessionId,SimpMessagingTemplate messagingTemplate) {
        this.messageQueue = new LinkedBlockingQueue<>();
        this.sessionId = sessionId;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run() {

        try {
            while(true) {
                if (startGame) {
                ChessMove message = messageQueue.take().move();
                System.out.println("Session:" + message);
                sendMessage(new ChessMove(7 - message.getFromCellX(),7 - message.getFromCellY(),"P",7 - message.getToCellX(),7 - message.getToCellY()),immutableList.get(0));
                }
                Thread.sleep(1_000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void receiveMessage(InformationOfChessMove message) {
        messageQueue.offer(message);
    }

    public void registerUser(String userSessionId) {
        tempList.add(userSessionId);
        if (tempList.size() == 1) {
            immutableList = Collections.unmodifiableList(tempList);
            tempList = null;
            startGame = true;
        }

    }

    private void sendMessage(String message){
        messagingTemplate.convertAndSend("/topic/session/" + sessionId,message);
    }
    private void sendMessage(ChessMove move, String userSessionId){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(userSessionId);
        headerAccessor.setLeaveMutable(true);
        messagingTemplate.convertAndSendToUser(userSessionId,"/queue/message/",move,headerAccessor.getMessageHeaders());
        System.out.println("сообщение отправлено: " + userSessionId);
    }

}
