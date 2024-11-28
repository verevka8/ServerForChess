package com.spring.boot.serverforchess.rest.entity;

public class ResponseSessionInfo {
    private String sessionId;

    public ResponseSessionInfo(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

