package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TruckObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/20/16.
 */
public class TruckParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setTruck(context, json);
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

    private void setTruck(Context context,String json){
        try {
            JSONObject object = new JSONObject(json);
            if(object != null){
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.deleteTruck();
                JSONArray arrTruck = object.optJSONArray("plat_no");
                if(arrTruck != null){
                    for (int i=0; i < arrTruck.length();i++){
                        TruckObject truckObject = new TruckObject();
                        JSONObject obj = arrTruck.optJSONObject(i);
                        String platNo = obj.optString("1");
                        String code = obj.optString("0");
                        truckObject.setPlatNo(platNo);
                        truckObject.setPlatNoCode(code);
                        dbHelper.createTruck(truckObject);
                    }
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
