package com.ikt.main.to.object;

/**
 * Created by arifins on 3/20/17.
 */

public class ScanVINObject {

    private String vin;
    private boolean status;
    private String message;
    private int offer;

    public ScanVINObject(String vin, boolean status, String message, int offer) {
        this.vin = vin;
        this.status = status;
        this.message = message;
        this.offer = offer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }
}
