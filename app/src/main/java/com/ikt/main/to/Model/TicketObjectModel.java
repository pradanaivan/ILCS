package com.ikt.main.to.model;

import java.io.Serializable;

/**
 * Created by Arifin on 3/26/16.
 */
public class TicketObjectModel implements Serializable{

    private String id;
    private String announceId;
    private String driverId;
    private String sync_status;
    private String visitId;
    private String carrier;
    private String driverName;
    private String platNo;
    private String expVinsLoad;
    private String expVinsUnload;
    private String outgoingVoyage;
    private String outgoingVessel;
    private String startTime;
    private String endTime;
    private String loadAreaIn;
    private String loadAreaOut;
    private String announceTime;
    private String operationTime;
    private String completeTime;
    private String leftTime;
    private String latestVisitStatus;
    private String orgId;
    private String lastChangeTime;
    private String assignAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(String announceId) {
        this.announceId = announceId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getSync_status() {
        return sync_status;
    }

    public void setSync_status(String sync_status) {
        this.sync_status = sync_status;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getExpVinsLoad() {
        return expVinsLoad;
    }

    public void setExpVinsLoad(String expVinsLoad) {
        this.expVinsLoad = expVinsLoad;
    }

    public String getExpVinsUnload() {
        return expVinsUnload;
    }

    public void setExpVinsUnload(String expVinsUnload) {
        this.expVinsUnload = expVinsUnload;
    }

    public String getOutgoingVoyage() {
        return outgoingVoyage;
    }

    public void setOutgoingVoyage(String outgoingVoyage) {
        this.outgoingVoyage = outgoingVoyage;
    }

    public String getOutgoingVessel() {
        return outgoingVessel;
    }

    public void setOutgoingVessel(String outgoingVessel) {
        this.outgoingVessel = outgoingVessel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoadAreaIn() {
        return loadAreaIn;
    }

    public void setLoadAreaIn(String loadAreaIn) {
        this.loadAreaIn = loadAreaIn;
    }

    public String getLoadAreaOut() {
        return loadAreaOut;
    }

    public void setLoadAreaOut(String loadAreaOut) {
        this.loadAreaOut = loadAreaOut;
    }

    public String getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(String announceTime) {
        this.announceTime = announceTime;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getAssignAt() {
        return assignAt;
    }

    public void setAssignAt(String assignAt) {
        this.assignAt = assignAt;
    }
}
