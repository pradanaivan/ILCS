package com.ikt.main.to.object;

import java.io.Serializable;

/**
 * Created by Arifin on 3/20/16.
 */
public class TruckObject implements Serializable{

    private long id;
    private String platNo;
    private String platNoCode;

    public String getPlatNoCode() {
        return platNoCode;
    }

    public void setPlatNoCode(String platNoCode) {
        this.platNoCode = platNoCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }
}
