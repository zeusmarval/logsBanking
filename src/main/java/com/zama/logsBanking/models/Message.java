package com.zama.logsBanking.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Message {

    @Id
    private String id;
    private LocalDateTime dateT;
    private String origin;
    private String messageType;
    private String message;

    public Message(LocalDateTime dateT, String origin, String messageType, String message) {
        this.dateT = dateT;
        this.origin = origin;
        this.messageType = messageType;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateT() {
        return dateT;
    }

    public void setDateT(LocalDateTime dateT) {
        this.dateT = dateT;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
