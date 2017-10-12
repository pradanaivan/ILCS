package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/24/16.
 */
public class AddDriverParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
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
//        {"data":{"ID":"30","NAME":"syams","PHONE":"0852134679","KTP":"741236985","SIM":"0963214785"},"status":true}
        try {
            DBHelper dbHelper = new DBHelper(context);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObj = jsonObject.optJSONObject("data");
            if(dataObj != null) {
                String id = dataObj.optString("ID");
                String name = dataObj.optString("NAME");
                String phone = dataObj.optString("PHONE");
                String ktp = dataObj.optString("KTP");
                String sim = dataObj.optString("SIM");
                String username = dataObj.optString("USERNAME");
                DriverObject driverObject = new DriverObject();
                driverObject.setDriverPhone(phone);
                driverObject.setLicenseNo(sim);
                driverObject.setDriverName(name);
                driverObject.setDriverId(id);
                driverObject.setIdentityNo(ktp);
                driverObject.setUsername(username);
                dbHelper.createAccountDriver(driverObject);
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }

}
