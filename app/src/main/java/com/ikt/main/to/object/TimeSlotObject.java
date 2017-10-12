package com.ikt.main.to.object;

import java.io.Serializable;

/**
 * Created by Arifin on 3/15/16.
 */
public class TimeSlotObject implements Serializable{

    private String timeSlotCode;
    private String timeSlotNR;
    private String timeSlotBookDate;
    private String timeSlotPosition;
    private String timeSlotStartAt;
    private String timeSlotEndAt;

    public String getTimeSlotCode() {
        return timeSlotCode;
    }

    public void setTimeSlotCode(String timeSlotCode) {
        this.timeSlotCode = timeSlotCode;
    }

    public String getTimeSlotNR() {
        return timeSlotNR;
    }

    public void setTimeSlotNR(String timeSlotNR) {
        this.timeSlotNR = timeSlotNR;
    }

    public String getTimeSlotBookDate() {
        return timeSlotBookDate;
    }

    public void setTimeSlotBookDate(String timeSlotBookDate) {
        this.timeSlotBookDate = timeSlotBookDate;
    }

    public String getTimeSlotPosition() {
        return timeSlotPosition;
    }

    public void setTimeSlotPosition(String timeSlotPosition) {
        this.timeSlotPosition = timeSlotPosition;
    }

    public String getTimeSlotStartAt() {
        return timeSlotStartAt;
    }

    public void setTimeSlotStartAt(String timeSlotStartAt) {
        this.timeSlotStartAt = timeSlotStartAt;
    }

    public String getTimeSlotEndAt() {
        return timeSlotEndAt;
    }

    public void setTimeSlotEndAt(String timeSlotEndAt) {
        this.timeSlotEndAt = timeSlotEndAt;
    }

}
