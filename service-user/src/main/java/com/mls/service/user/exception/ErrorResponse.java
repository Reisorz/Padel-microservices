package com.mls.service.user.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorResponse {
    private Integer status;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponse(LocalDateTime timestamp, String details, String message, Integer status) {
        this.timestamp = timestamp;
        this.details = details;
        this.message = message;
        this.status = status;
    }

    public ErrorResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
