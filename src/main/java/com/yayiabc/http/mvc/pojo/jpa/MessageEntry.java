package com.yayiabc.http.mvc.pojo.jpa;

public class MessageEntry {
    private String message;
    private String type;
    private String typeId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public MessageEntry() {
    }

    public MessageEntry(String message, String type, String typeId) {
        this.message = message;
        this.type = type;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "MessageEntry{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
