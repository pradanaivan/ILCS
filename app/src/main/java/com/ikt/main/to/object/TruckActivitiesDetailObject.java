package com.ikt.main.to.object;

import java.io.Serializable;

/**
 * Created by Arifin on 3/26/16.
 */
public class TruckActivitiesDetailObject implements Serializable{
//    "VISIT_ID": "TRK12201496053112093",
//    "DRIVER_ID": "21",
//    "ASSIGN_AT": null,
//    "OPERATION": null,
//    "COMPLETE": null,
//    "LEFT": null,
//    "LATEST_VISIT_STATUS": "ANNOUNCED",
//    "LAST_CHANGE": "22-MAR-16 01.46.10.000000 PM"
//http://103.19.81.26:5180/check/index.php/api/GetActivity
//{"VISIT_ID":"TRK31602018784325625","DRIVER":"ervv","TRUCK_CODE":"B9003WV","EXP_VINS_LOADING":"0","OUTGOING_VOYAGE_NR":null,"OUTGOING_VESSEL":null,"EXP_VINS_UNLOADING":"12","TIME_BEGIN":"31-MAR-2016 06:00","TIME_END":"31-MAR-2016 11:00","LOADAREA_IN":"ED003","LOADAREA_OUT":null,"ANNOUNCE":"30-MAR-2016 23:30","OPERATION":null,"COMPLETE":null,"LEFT":null}


    private String visitId;
    private String driverId;
    private String driver;
    private String truckCode;
    private String exp_vins_loading;
    private String outgoing_voyage_nr;
    private String outgoing_vessel;
    private String exp_vins_unloading;
    private String time_begin;
    private String time_end;
    private String loadarea_out;
    private String loadarea_in;
    private String announce;
    private String operation;
    private String complete;
    private String left;

    private String assignAt;
    private String operationTime;
    private String completeTime;
    private String leftTime;
    private String latestVisitStatus;
    private String lastChange;

    //{"LAST_UPDATE":"31-MAR-2016 15:59:40.000000","TRUCK":"B9004WV","DRIVER":"dbdfvdf","LAST_STATUS":"LEFT"}

    private String last_update;
    private String truck;

    private String last_status;
    private String created_by;




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

    public java.lang.String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(java.lang.String truckCode) {
        this.truckCode = truckCode;
    }

    public java.lang.String getExp_vins_loading() {
        return exp_vins_loading;
    }

    public void setExp_vins_loading(java.lang.String exp_vins_loading) {
        this.exp_vins_loading = exp_vins_loading;
    }

    public java.lang.String getOutgoing_voyage_nr() {
        return outgoing_voyage_nr;
    }

    public void setOutgoing_voyage_nr(java.lang.String outgoing_voyage_nr) {
        this.outgoing_voyage_nr = outgoing_voyage_nr;
    }

    public java.lang.String getOutgoing_vessel() {
        return outgoing_vessel;
    }

    public void setOutgoing_vessel(java.lang.String outgoing_vessel) {
        this.outgoing_vessel = outgoing_vessel;
    }

    public java.lang.String getExp_vins_unloading() {
        return exp_vins_unloading;
    }

    public void setExp_vins_unloading(java.lang.String exp_vins_unloading) {
        this.exp_vins_unloading = exp_vins_unloading;
    }

    public java.lang.String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(java.lang.String time_begin) {
        this.time_begin = time_begin;
    }

    public java.lang.String getTime_end() {
        return time_end;
    }

    public void setTime_end(java.lang.String time_end) {
        this.time_end = time_end;
    }

    public java.lang.String getLoadarea_out() {
        return loadarea_out;
    }

    public void setLoadarea_out(java.lang.String loadarea_out) {
        this.loadarea_out = loadarea_out;
    }

    public java.lang.String getAnnounce() {
        return announce;
    }

    public void setAnnounce(java.lang.String announce) {
        this.announce = announce;
    }

    public java.lang.String getOperation() {
        return operation;
    }

    public void setOperation(java.lang.String operation) {
        this.operation = operation;
    }

    public java.lang.String getComplete() {
        return complete;
    }

    public void setComplete(java.lang.String complete) {
        this.complete = complete;
    }

    public java.lang.String getLeft() {
        return left;
    }

    public void setLeft(java.lang.String left) {
        this.left = left;
    }

    public java.lang.String getLoadarea_in() {
        return loadarea_in;
    }

    public void setLoadarea_in(java.lang.String loadarea_in) {
        this.loadarea_in = loadarea_in;
    }

    public java.lang.String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(java.lang.String created_by) {
        this.created_by = created_by;
    }
}
