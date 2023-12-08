package com.zama.logsBanking.models.Mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("M_Message")
@Data
public class M_MessageMongo {
    private String id;
    private LocalDateTime dateT;
    private String origin;
    private String messageType;
    private String message;

    public M_MessageMongo() {
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

    public String getOrigin(String queueNameTransactions) {
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

    @Override
    public String toString() {
        return "M_MessageMongo{" +
                "id='" + id + '\'' +
                ", dateT=" + dateT +
                ", origin='" + origin + '\'' +
                ", messageType='" + messageType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
