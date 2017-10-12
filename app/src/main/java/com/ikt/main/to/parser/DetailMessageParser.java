package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.util.APPException;
import com.ikt.main.to.util.PreferenceManagers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 6/1/16.
 */
public class DetailMessageParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setMessage(context,json);
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

    private void setMessage(Context context, String json){
        try {
            JSONObject object = new JSONObject(json);
            JSONObject objData = object.optJSONObject("data");
            if(objData != null){
                String message = objData.optString("MESSAGE");
                PreferenceManagers.setDataWithSameKey(message, "detailMsg", context);
            }
        }catch (JSONException e){
            setError(e.getMessage());
        }
    }
}
