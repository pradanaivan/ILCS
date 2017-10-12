package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 9/28/16.
 */

public class VinParser extends DefaultErrorModel implements IParser {


    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setData(context,json);
        } catch (APPException e) {
            setError(e.getMessage());
        }
    }

    private void setData(Context context, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arr = jsonObject.optJSONArray("vin");
            VectorModel.getInstance().clearArrVin();
            if(arr != null && arr.length() > 0){
                for(int i=0; i < arr.length(); i++){
                    String vin = arr.optString(i);
                    VectorModel.getInstance().setArrVin(vin);
                }
            }
        }catch (JSONException e){
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
}
