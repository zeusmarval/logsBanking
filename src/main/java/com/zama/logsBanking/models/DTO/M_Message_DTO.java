package com.zama.logsBanking.models.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class M_Message_DTO {
    private String id;
    private LocalDateTime dateT;
    private String origin;
    private String messageType;
    private String message;

    public M_Message_DTO(LocalDateTime dateT, String origin, String messageType, String message) {
        this.dateT = dateT;
        this.origin = origin;
        this.messageType = messageType;
        this.message = message;
    }
}
