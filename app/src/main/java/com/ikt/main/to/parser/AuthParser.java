package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.util.APPException;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/20/16.
 */
public class AuthParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
            setProfile(context, json);
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

    private void setProfile(Context context, String json){
//        {
//            "USERID": "41",
//                "NAME": "ADMIN",
//                "USERNAME": "admin",
//                "GROUPNAME": "TRUCK OPERATOR",
//                "ORG_ID": "3959341771344691265",
//                "ORG_CODE": "DUNEXTR",
//                "ORG_NAME": "DUNIA EXPRESS TRANSINDO",
//                "SESSION_ID": 1458453749
//        }
        JSONObject obj;
        try {
            DBHelper db = new DBHelper(context);
//            db.deleteProfile();
            obj = new JSONObject(json);
            String userId = obj.optString("USERID");
            String username = obj.optString("USERNAME");
            String name = obj.optString("NAME");
            String grupName = obj.optString("GROUPNAME");
            String orgId = obj.optString("ORG_ID");
            String orgCode = obj.optString("ORG_CODE");
            String session = obj.optString("SESSION_ID");
            String orgName = obj.optString("ORG_NAME");
            String previlige = obj.optString("PRIVILEGE");

            ProfileObject profileObject = new ProfileObject();
            profileObject.setName(name);
            profileObject.setSession(session);
            profileObject.setOrgName(orgName);
            profileObject.setOrgId(orgId);
            profileObject.setUserId(userId);
            profileObject.setUsername(username);
            profileObject.setOrgCode(orgCode);
            profileObject.setPrevilige(previlige);

            db.createProfile(profileObject);

            PreferenceManagers.setDataWithSameKey(json, Config.KEY_PROFILE, context);
            PreferenceManagers.setDataWithSameKey(orgId, Config.KEY_ORG_ID, context);
            PreferenceManagers.setDataWithSameKey(userId, Config.KEY_USER_ID,context);
            PreferenceManagers.setDataWithSameKey(username, Config.KEY_USERNAME,context);
            PreferenceManagers.setDataWithSameKey(session, Config.KEY_SESSION,context);
            PreferenceManagers.setDataWithSameKey("true",Config.IS_LOGIN,context);
            PreferenceManagers.setDataWithSameKey(previlige,Config.KEY_PRIVILEGE, context);
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }

}
