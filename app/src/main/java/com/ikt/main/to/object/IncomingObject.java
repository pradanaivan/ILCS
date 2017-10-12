package com.ikt.main.to.object;

/**
 * Created by Arifin on 3/20/16.
 */
public class IncomingObject {

//    {"VISIT_ID":"TRK1611466502561678","SUPIR":"HABIB","PLAT_NO":"B9001WV","TYPE":"ANNOUNCED","status":true}
    private String visitId;
    private String supir;
    private String platNo;
    private String type;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getSupir() {
        return supir;
    }

    public void setSupir(String supir) {
        this.supir = supir;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
