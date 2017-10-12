package com.ikt.main.to.object;

/**
 * Created by Arifin on 4/10/16.
 */
public class TicketObject {

//    "VISIT_ID": "TRK4280980226581509",
//            "TRUCK": "B9003WV",
//            "DRIVER": "dfg",
//            "BEGIN": "30-MAR-2016 13:00",
//            "END": "30-MAR-2016 17:00"
//            "TYPE": "INCOMING"
    private String visitId;
    private String platNo;
    private String driverName;
    private String begin;
    private String end;
    private String type;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
