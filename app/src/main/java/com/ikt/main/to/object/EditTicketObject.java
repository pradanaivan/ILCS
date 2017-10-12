package com.ikt.main.to.object;

/**
 * Created by Arifin on 9/25/16.
 */

public class EditTicketObject {
//            {
//                "DRIVER": "Driver 04",
//                    "DRIVER_ID": "21",
//                    "TRUCK_CODE": "B9030UIN",
//                    "LICENSE_PLATE": null,
//                    "TYPE": "INCOMING",
//                    "TERMINAL": "INTERNATIONAL",
//                    "TRIP_OR_VIN": "vin",
//                    "EXPECTED_VINS_LOADING" : "6,
//                    "EXPECTED_VINS_UNLOADING" : "2"
//                    "status": true
//            }

    private String driver;
    private String driverId;
    private String truckCode;
    private String licencePlate;
    private String type;
    private String terminal;
    private String tripOrVin;
    private String expectedLoading;
    private String expectedUnloading;
    private boolean status;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTripOrVin() {
        return tripOrVin;
    }

    public void setTripOrVin(String tripOrVin) {
        this.tripOrVin = tripOrVin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExpectedLoading() {
        return expectedLoading;
    }

    public void setExpectedLoading(String expectedLoading) {
        this.expectedLoading = expectedLoading;
    }

    public String getExpectedUnloading() {
        return expectedUnloading;
    }

    public void setExpectedUnloading(String expectedUnloading) {
        this.expectedUnloading = expectedUnloading;
    }
}
