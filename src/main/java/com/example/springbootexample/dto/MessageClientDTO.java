package com.example.springbootexample.dto;

import java.sql.Timestamp;

public class MessageClientDTO {
    private String message;
    private String userId;
    private Timestamp createAt = new Timestamp(System.currentTimeMillis());

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
