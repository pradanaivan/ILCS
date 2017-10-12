package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TruckActivitiesDetailObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/26/16.
 */
public class TruckActivitiesDetailParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
            setData(context, json);
        } catch (APPException e) {
            setError(e.getMessage());
        }
    }

    private void checkError(Context context, String json) throws APPException {
        try {
            if (json == null || json.isEmpty()) {
                setError("No Response from server");
                throw new APPException(getErrorMessage());
            }

            isValidJSON(json);
            JSONObject obj = new JSONObject(json);
            if (obj != null) {
                boolean status = obj.optBoolean("status");
                String msg = obj.optString("message");
                if (!status) {
                    setError(msg);
                    throw new APPException(getErrorMessage());
                }
            }
        } catch (JSONException e) {
            Log.e(DefaultErrorModel.class.getSimpleName(), String.format("JSON Exception : ", e.getMessage()));
            setError(e.getMessage());
            throw new APPException(getErrorMessage());
        }
    }

    private void setData(Context context,String json){
//        "VISIT_ID": "TRK12201496053112093",
//        "DRIVER_ID": "21",
//        "ASSIGN_AT": null,
//        "OPERATION": null,
//        "COMPLETE": null,
//        "LEFT": null,
//        "LATEST_VISIT_STATUS": "ANNOUNCED",
//        "LAST_CHANGE": "22-MAR-16 01.46.10.000000 PM"
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arrData =  jsonObject.optJSONArray("data");
            if(arrData != null){
                VectorModel.getInstance().clearTruckDetailObjects();
                for (int i=0; i < arrData.length();i++){
                    JSONObject objData = arrData.optJSONObject(i);
                    TruckActivitiesDetailObject activitiesDetailObject = new TruckActivitiesDetailObject();
                    activitiesDetailObject.setVisitId(objData.optString("VISIT_ID"));
                    activitiesDetailObject.setDriver(objData.optString("DRIVER"));
                    activitiesDetailObject.setTruckCode(objData.optString("TRUCK_CODE"));
                    activitiesDetailObject.setExp_vins_loading(objData.optString("EXP_VINS_LOADING"));
                    activitiesDetailObject.setOutgoing_voyage_nr(objData.optString("OUTGOING_VOYAGE_NR"));
                    activitiesDetailObject.setOutgoing_vessel(objData.optString("OUTGOING_VESSEL"));
                    activitiesDetailObject.setExp_vins_loading(objData.optString("EXP_VINS_UNLOADING"));
                    activitiesDetailObject.setTime_begin(objData.optString("TIME_BEGIN"));

                    activitiesDetailObject.setTime_end(objData.optString("TIME_END"));
                    activitiesDetailObject.setLoadarea_in(objData.optString("LOADAREA_IN"));
                    activitiesDetailObject.setLoadarea_out(objData.optString("LOADAREA_OUT"));
                    activitiesDetailObject.setAnnounce(objData.optString("ANNOUNCE"));
                    activitiesDetailObject.setOperation(objData.optString("OPERATION"));
                    activitiesDetailObject.setComplete(objData.optString("COMPLETE"));
                    activitiesDetailObject.setLeft(objData.optString("LEFT"));
                    activitiesDetailObject.setCreated_by(objData.optString("CREATED_BY"));

                    VectorModel.getInstance().setTruckActivitiesDetailObjects(activitiesDetailObject);
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
