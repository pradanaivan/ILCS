package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.ScanVINObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arifins on 3/20/17.
 */

public class CheckScanVINParser extends DefaultErrorModel implements IParser{
    @Override
    public void parser(Context context, int pid, String json) {
        try {
//            Toast.makeText(context, "response="+json, Toast.LENGTH_SHORT).show();
            checkError(context, json);
            setResultScan(context, json);
        } catch (APPException e) {
            setError(e.getMessage());
        }
    }

    private void setResultScan(Context context, String json) {
        try {
            VectorModel.getInstance().clearDatascanObject();
            JSONObject object = new JSONObject(json);
            ScanVINObject scanVINObject = new ScanVINObject(object.optString("vin"), object.optBoolean("status"), object.optString("message"), object.optInt("offer"));
            VectorModel.getInstance().setScanVINObjects(scanVINObject);
        } catch (JSONException e) {
            Log.e(DefaultErrorModel.class.getSimpleName(), String.format("JSON Exception : ", e.getMessage()));
            setError(e.getMessage());
        }
    }

    private void checkError(Context context, String json) throws APPException{
        try {
            if (json == null || json.isEmpty()) {
                setError("No Response from server");
                throw new APPException(getErrorMessage());
            }

            isValidJSON(json);
            JSONObject obj = new JSONObject(json);
            if (obj != null) {
                boolean status = obj.optBoolean("status");
                int offer = obj.optInt("offer");
                String msg = obj.optString("message");
                if (!status && offer != 1) {
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
