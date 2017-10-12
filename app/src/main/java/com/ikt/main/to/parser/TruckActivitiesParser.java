package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TruckActivitiesObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/26/16.
 */
public class TruckActivitiesParser extends DefaultErrorModel implements IParser {

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
                VectorModel.getInstance().clearTruckActivitiesObjects();
                for (int i=0; i < arrData.length();i++){
                    JSONObject objData = arrData.optJSONObject(i);
                    TruckActivitiesObject activitiesObject = new TruckActivitiesObject();
                    activitiesObject.setAssignAt(objData.optString("ASSIGN_AT"));
                    activitiesObject.setVisitId(objData.optString("VISIT_ID"));
                    activitiesObject.setDriverId(objData.optString("DRIVER_ID"));
                    activitiesObject.setOperationTime(objData.optString("OPERATION"));
                    activitiesObject.setCompleteTime(objData.optString("COMPlETE"));
                    activitiesObject.setLeftTime(objData.optString("LEFT"));
                    activitiesObject.setLatestVisitStatus(objData.optString("LATEST_VISIT_STATUS"));
                    activitiesObject.setLastChange(objData.optString("LAST_CHANGE"));

                    activitiesObject.setLast_update(objData.optString("LAST_UPDATE"));
                    activitiesObject.setTruck(objData.optString("TRUCK"));
                    activitiesObject.setDriver(objData.optString("DRIVER"));
                    activitiesObject.setLast_status(objData.optString("LAST_STATUS"));

                    VectorModel.getInstance().setTruckActivitiesObjects(activitiesObject);
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
