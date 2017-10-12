package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arifins on 3/29/17.
 */

public class SlotParser extends DefaultErrorModel implements IParser{

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
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
//        {
//            "date": [
//            "Kamis, 30-03-2017",
//                    "Jumat, 31-03-2017",
//                    "Sabtu, 01-04-2017"
//            ],
//            "slot": [
//            [
//            "MORNING 08.00 - 12.00",
//                    "AFTERNOON 12.00 - 17.00",
//                    "EVENING 19.00 - 03.00"
//            ],
//            [
//            "MORNING 08.00 - 12.00",
//                    "AFTERNOON 12.00 - 17.00",
//                    "EVENING 19.00 - 03.00"
//            ],
//            [
//            "MORNING 08.00 - 12.00",
//                    "AFTERNOON 12.00 - 17.00",
//                    "EVENING 19.00 - 03.00"
//            ]
//            ],
//            "area": [
//            "D"
//            ],
//            "status": true
//        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray dateArr = jsonObject.optJSONArray("date");
            if(dateArr != null){
                VectorModel.getInstance().clearObjectNewSlotDateIn();
                for (int i = 0; i < dateArr.length(); i++) {
                    String item = dateArr.optString(i);
                    VectorModel.getInstance().setObjectNewSlotsDateIn(item);
                }
            }
            JSONArray slotArr = jsonObject.optJSONArray("slot");
            if(dateArr != null){
                VectorModel.getInstance().clearObjectNewSlotSlotIn();
                for (int i = 0; i < slotArr.length(); i++) {
                    JSONArray slotDetailArr = slotArr.optJSONArray(i);
                    String item = slotDetailArr.toString();
                    VectorModel.getInstance().setObjectNewSlotsSlotIn(item);
                }
            }
            JSONArray areaArr = jsonObject.optJSONArray("area");
            if(dateArr != null){
                VectorModel.getInstance().clearObjectNewSlotAreaIn();
                for (int i = 0; i < areaArr.length(); i++) {
                    String item = areaArr.optString(i);
                    VectorModel.getInstance().setObjectNewSlotsAreaIn(item);
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
