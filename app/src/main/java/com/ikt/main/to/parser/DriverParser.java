package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/20/16.
 */
public class DriverParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setDriver(context,json);
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

    private void setDriver(Context context,String json){
//        {
//            "data": [
//            {
//                "ID": "2",
//                    "USER_ID": "42",
//                    "NAME": "SAMSUL",
//                    "PHONE": "2342343",
//                    "KTP": "34324",
//                    "NO_SIM": "2334",
//                    "CREATED_BY": "41",
//                    "ORGANIZATION_ID": "3959341771344691265"
//            },
//            {
//                "ID": "1",
//                    "USER_ID": "41",
//                    "NAME": "HABIB",
//                    "PHONE": "085211950343",
//                    "KTP": "23234",
//                    "NO_SIM": "2343",
//                    "CREATED_BY": "41",
//                    "ORGANIZATION_ID": "3959341771344691265"
//            }
//            ],
//            "status": true
//        }

        try {
            JSONObject object = new JSONObject(json);
            DBHelper dbHelper = new DBHelper(context);
            int sum = object.optInt("sum");
            JSONArray arrData = object.optJSONArray("data");
            if(arrData != null){
                for (int i=0; i < arrData.length(); i++){
                    JSONObject dataObj = arrData.optJSONObject(i);
                    String driverId = dataObj.optString("ID");
                    String name = dataObj.optString("NAME");
                    String phone = dataObj.optString("PHONE");
                    String identity = dataObj.optString("KTP");
                    String license = dataObj.optString("NO_SIM");
                    String username = dataObj.optString("USERNAME");
                    DriverObject driverObject = new DriverObject();
                    driverObject.setDriverPhone(phone);
                    driverObject.setLicenseNo(license);
                    driverObject.setDriverName(name);
                    driverObject.setDriverId(driverId);
                    driverObject.setIdentityNo(identity);
                    driverObject.setUsername(username);
                    dbHelper.createAccountDriver(driverObject);
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
