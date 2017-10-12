package com.ikt.main.to.object;

/**
 * Created by arifins on 3/19/17.
 */

public class SimpleObject {

    private int status;
    private String message;

    public SimpleObject(int status, String message) {
        this.status = status;
        this.message = message;
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
}
