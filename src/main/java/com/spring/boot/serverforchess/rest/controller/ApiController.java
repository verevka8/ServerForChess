package com.spring.boot.serverforchess.rest.controller;



import com.spring.boot.serverforchess.rest.entity.ResponseSessionInfo;
import com.spring.boot.serverforchess.session.Session;
import com.spring.boot.serverforchess.session.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {
    private final SessionController sessionController;
    private int s = 0; // TODO: написать генерацию уникального индекфикатора сессии

    @Autowired
    public ApiController(SessionController sessionController){
        this.sessionController = sessionController;
    }

    @GetMapping("/createSession")
    public ResponseSessionInfo createSession() {
        String sessionId = "792bc4d3-cfae5468" + s++; // TODO: написать генерацию уникального индекфикатора сессии
        sessionController.createNewSession(sessionId);
        return new ResponseSessionInfo("Ok!",sessionId);
    }

    @GetMapping("/sessions")
    public Collection<Session> getSessions(){
        return sessionController.getSessions();
    }
}