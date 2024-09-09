package com.spring.boot.serverforchess.rest.entity;

public class ResponseSessionInfo {
    private String stateOfCreation;
    private String sessionId;

    public ResponseSessionInfo(String stateOfCreation, String sessionId) {
        this.stateOfCreation = stateOfCreation;
        this.sessionId = sessionId;
    }

    public String getStateOfCreation() {
        return stateOfCreation;
    }

    public void setStateOfCreation(String stateOfCreation) {
        this.stateOfCreation = stateOfCreation;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

