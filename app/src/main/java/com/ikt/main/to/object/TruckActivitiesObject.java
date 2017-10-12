package com.ikt.main.to.object;

import java.io.Serializable;

/**
 * Created by Arifin on 3/26/16.
 */
public class TruckActivitiesObject implements Serializable{
//    "VISIT_ID": "TRK12201496053112093",
//    "DRIVER_ID": "21",
//    "ASSIGN_AT": null,
//    "OPERATION": null,
//    "COMPLETE": null,
//    "LEFT": null,
//    "LATEST_VISIT_STATUS": "ANNOUNCED",
//    "LAST_CHANGE": "22-MAR-16 01.46.10.000000 PM"

    private String visitId;
    private String driverId;
    private String assignAt;
    private String operationTime;
    private String completeTime;
    private String leftTime;
    private String latestVisitStatus;
    private String lastChange;

    //{"LAST_UPDATE":"31-MAR-2016 15:59:40.000000","TRUCK":"B9004WV","DRIVER":"dbdfvdf","LAST_STATUS":"LEFT"}

    private String last_update;
    private String truck;
    private String driver;
    private String last_status;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getAssignAt() {
        return assignAt;
    }

    public void setAssignAt(String assignAt) {
        this.assignAt = assignAt;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public String getLatestVisitStatus() {
        return latestVisitStatus;
    }

    public void setLatestVisitStatus(String latestVisitStatus) {
        this.latestVisitStatus = latestVisitStatus;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getLast_status() {
        return last_status;
    }

    public void setLast_status(String last_status) {
        this.last_status = last_status;
    }
}
