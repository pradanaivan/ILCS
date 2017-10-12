package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.EditTicketObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 9/25/16.
 */

public class RetrieveEditVisitParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setData(context,json);
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

    private void setData(Context context, String json){
        try {
//            {
//                "DRIVER": "Driver 04",
//                    "DRIVER_ID": "21",
//                    "TRUCK_CODE": "B9030UIN",
//                    "LICENSE_PLATE": null,
//                    "TYPE": "INCOMING",
//                    "TERMINAL": "INTERNATIONAL",
//                    "TRIP_OR_VIN": "vin",
//                    "status": true
//            }
            JSONObject jsonObject = new JSONObject(json);
            if(jsonObject != null){
                VectorModel.getInstance().clearEditTicketObjects();
                EditTicketObject editTicketObject = new EditTicketObject();
                editTicketObject.setDriver(jsonObject.optString("DRIVER"));
                editTicketObject.setDriverId(jsonObject.optString("DRIVER_ID"));
                editTicketObject.setLicencePlate(jsonObject.optString("LICENSE_PLATE"));
                editTicketObject.setTruckCode(jsonObject.optString("TRUCK_CODE"));
                editTicketObject.setType(jsonObject.optString("TYPE"));
                editTicketObject.setTerminal(jsonObject.optString("TERMINAL"));
                editTicketObject.setStatus(jsonObject.optBoolean("status"));
                editTicketObject.setTripOrVin(jsonObject.optString("TRIP_OR_VIN"));
                editTicketObject.setExpectedLoading(jsonObject.optString("EXPECTED_VINS_LOADING"));
                editTicketObject.setExpectedUnloading(jsonObject.optString("EXPECTED_VINS_UNLOADING"));
                VectorModel.getInstance().setEditTicketObjects(editTicketObject);
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
