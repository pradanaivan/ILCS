package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.IncomingObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/20/16.
 */
public class IncomingParser extends DefaultErrorModel implements IParser{
    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setAnnounce(context, json);
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
                String err = obj.optString("ERROR");
                if(err.length() == 0)
                    err = obj.optString("message");
                if (!status) {
                    setError(err);
                    throw new APPException(getErrorMessage());
                }
            }
        } catch (JSONException e) {
            Log.e(DefaultErrorModel.class.getSimpleName(), String.format("JSON Exception : ", e.getMessage()));
            setError(e.getMessage());
            throw new APPException(getErrorMessage());
        }
    }

    private void setAnnounce(Context context, String json){
        //    {"VISIT_ID":"TRK1611466502561678","SUPIR":"HABIB","PLAT_NO":"B9001WV","TYPE":"ANNOUNCED","status":true}
        try {
            JSONObject object = new JSONObject(json);
            if(object != null){
                VectorModel.getInstance().clearIncomingObjects();
                String visitId = object.optString("VISIT_ID");
                String supir = object.optString("SUPIR");
                String platNo = object.optString("PLAT_NO");
                String type = object.optString("TYPE");
                IncomingObject incomingObject = new IncomingObject();
                incomingObject.setPlatNo(platNo);
                incomingObject.setSupir(supir);
                incomingObject.setType(type);
                incomingObject.setVisitId(visitId);
                VectorModel.getInstance().setIncomingObjects(incomingObject);
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }

    }
}
