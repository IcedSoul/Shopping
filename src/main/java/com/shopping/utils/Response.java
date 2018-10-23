package com.shopping.utils;

/**
 * Created by IcedSoul on 2018/4/8.
 */

public class Response {
    public int status;
    public String message;
    public Object content;

    public Response(int status, String message, Object content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
