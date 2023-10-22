package com.devsk.PDF.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meta {

    private String transactionID;

    private String statusCode;

    private int status;

    private String message;

    private String devMessage;

    public Meta(String statusCode, int status) {
        this.transactionID = UUID.randomUUID().toString();
        this.statusCode = statusCode;
        this.status = status;
        this.message = "";
        this.devMessage = "";
    }

    public Meta(String statusCode, int status, String message) {
        this.transactionID = UUID.randomUUID().toString();
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.devMessage = "";
    }

    public Meta(String statusCode, int status, String message, String devMessage) {
        this.transactionID = UUID.randomUUID().toString();
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.devMessage = devMessage;
    }
}
