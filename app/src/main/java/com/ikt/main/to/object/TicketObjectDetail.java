package com.ikt.main.to.object;

/**
 * Created by Arifin on 4/10/16.
 */
public class TicketObjectDetail {

//    "data": [{
//        "CARRIER": "PT. DUNIA EXPRESS TRANSINDO",
//                "DRIVER": "dfg",
//                "TRUCK": "B9003WV",
//                "OUTGOING_VOYAGE_NR": null,
//                "OUTGOING_VESSEL": null,
//                "TIME_BEGIN": "30-MAR-2016 13:00",
//                "TIME_END": "30-MAR-2016 17:00",
//                "LOADAREA_IN": "CD005",
//                "LOADAREA_OUT": null,
//                "AMOUNT_CAR_PICKUP": "0",
//                "AMOUNT_CAR_VESSEL": "9"
//    }],

    private String carrier;
    private String driverName;
    private String platNo;
    private String outGoingVoyage;
    private String outGoingVessel;
    private String begin;
    private String end;
    private String areaIn;
    private String areaOut;
    private String amountCarPickup;
    private String amountCarVessel;

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

    public String getOutGoingVoyage() {
        return outGoingVoyage;
    }

    public void setOutGoingVoyage(String outGoingVoyage) {
        this.outGoingVoyage = outGoingVoyage;
    }

    public String getOutGoingVessel() {
        return outGoingVessel;
    }

    public void setOutGoingVessel(String outGoingVessel) {
        this.outGoingVessel = outGoingVessel;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAreaIn() {
        return areaIn;
    }

    public void setAreaIn(String areaIn) {
        this.areaIn = areaIn;
    }

    public String getAreaOut() {
        return areaOut;
    }

    public void setAreaOut(String areaOut) {
        this.areaOut = areaOut;
    }

    public String getAmountCarPickup() {
        return amountCarPickup;
    }

    public void setAmountCarPickup(String amountCarPickup) {
        this.amountCarPickup = amountCarPickup;
    }

    public String getAmountCarVessel() {
        return amountCarVessel;
    }

    public void setAmountCarVessel(String amountCarVessel) {
        this.amountCarVessel = amountCarVessel;
    }
}
