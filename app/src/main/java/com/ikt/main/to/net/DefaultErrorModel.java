package com.ikt.main.to.net;

import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DefaultErrorModel {
    
	protected String mErrorMsg = null;
    protected int mErrorCode = -1;

    public void setError(String errorMessage) {
        this.mErrorMsg = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMsg;
    }

    public boolean isError() {
        return mErrorMsg != null;
    }

    protected void isValidJSON(String json) throws APPException {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            setError("Unknown Response from server");
            throw new APPException(getErrorMessage());
        }
    }

    protected void isValidJSONArr(String json) throws APPException {
        try {
            new JSONArray(json);
        } catch (JSONException e) {
            setError("Unknown Response from server");
            throw new APPException(getErrorMessage());
        }
    }

    
}
