package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/20/16.
 */
public class TimeSlotParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
            setTimeSlot(context, json);
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

    private void setTimeSlot(Context context,String json){
        try {
//            {
//                "0": "1092216",
//                    "1": "2016-03-20",
//                    "2": "C_EVENING_DELIVERY",
//                    "3": "CD008",
//                    "4": "2016-03-20T19:00",
//                    "5": "2016-03-21T03:00"
//            }
//            "0": "NR",
//                    "1": "BOOKDATE",
//                    "2": "CODE",
//                    "3": "POSITION",
//                    "4": "STARTAT",
//                    "5": "ENDAT"
            JSONObject object = new JSONObject(json);
            if(object != null){
                JSONArray array = object.optJSONArray("data");
                if(array.length() > 1){
                    VectorModel.getInstance().clearTimeSlotVector();
                    for(int i=1;i < array.length();i++){
                        TimeSlotObject timeSlotObject = new TimeSlotObject();
                        JSONObject arrData = array.optJSONObject(i);
                        String NR = arrData.optString("0");
                        String bookDate = arrData.optString("1");
                        String code = arrData.optString("2");
                        String position = arrData.optString("3");
                        String startAt = arrData.optString("4");
                        String endAt = arrData.optString("5");
                        timeSlotObject.setTimeSlotNR(NR);
                        timeSlotObject.setTimeSlotBookDate(bookDate);
                        timeSlotObject.setTimeSlotCode(code);
                        timeSlotObject.setTimeSlotPosition(position);
                        timeSlotObject.setTimeSlotStartAt(startAt);
                        timeSlotObject.setTimeSlotEndAt(endAt);
                        VectorModel.getInstance().setTimeSlotObjectVector(timeSlotObject);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
