package com.spring.boot.serverforchess.websocket.entity;

public class MessageWrapper {
    private String jsonObject;
    private String jsonType;

    public MessageWrapper(String jsonObject, String jsonType) {
        this.jsonObject = jsonObject;
        this.jsonType = jsonType;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getJsonType() {
        return jsonType;
    }

    public void setJsonType(String jsonType) {
        this.jsonType = jsonType;
    }
}
