package com.ikt.main.to.object;

import java.io.Serializable;

/**
 * Created by Arifin on 6/7/16.
 */
public class NotifObject implements Serializable{

    private int id;
    private String message;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
