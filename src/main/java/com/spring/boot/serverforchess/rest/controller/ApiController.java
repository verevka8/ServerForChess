package com.spring.boot.serverforchess.rest.controller;



import com.spring.boot.serverforchess.rest.entity.ResponseSessionCreation;
import com.spring.boot.serverforchess.websocket.controller.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiController {
    private SessionController controller;
    @Autowired
    public ApiController(SessionController controller) {
        this.controller = controller;
    }

    @GetMapping("/createSession")
    public ResponseSessionCreation createSession() {
        controller.createNewSession("1122"); // TODO: написать генерацию уникального индекфикатора сессии
        return new ResponseSessionCreation("Ok!","1122");
    }
}