package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.object.SimpleObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arifins on 3/19/17.
 */

public class CheckUpdateParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
            setData(context, json);
        } catch (APPException e) {
            setError(e.getMessage());
        }
    }

    private void setData(Context context, String json) {
        try{
            VectorModel.getInstance().clearCheckUpdate();
            JSONObject object = new JSONObject(json);
            SimpleObject simpleObject = new SimpleObject(object.optInt("status"), object.optString("message"));
            VectorModel.getInstance().setCheckUpdateObject(simpleObject);
        } catch (JSONException e) {
            setError("No Response from server");
        }
    }

    private void checkError(Context context, String json) throws APPException {
        if (json == null || json.isEmpty()) {
            setError("No Response from server");
            throw new APPException(getErrorMessage());
        }

        isValidJSON(json);
    }
}
