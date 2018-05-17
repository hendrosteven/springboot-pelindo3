package com.pelindo.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private boolean success;
    private List<String> messages = new ArrayList<>();
    private Object data;


    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
