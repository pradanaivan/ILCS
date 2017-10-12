package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TicketObject;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 3/26/16.
 */
public class TickerParser extends DefaultErrorModel implements IParser {

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context, json);
            setData(context,json);
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

    private void setData(Context context, String json){
        try {
//            "VISIT_ID": "TRK4280980226581509",
//                    "TRUCK": "B9003WV",
//                    "DRIVER": "dfg",
//                    "BEGIN": "30-MAR-2016 13:00",
//                    "END": "30-MAR-2016 17:00"
//                    "TYPE": "incoming"
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arrData = jsonObject.optJSONArray("assign");
            if(arrData != null){
                VectorModel.getInstance().clearListAssignTicketObjects();
                for(int i=0;i < arrData.length();i++){
                    JSONObject object = arrData.optJSONObject(i);
                    TicketObject ticketObject = new TicketObject();
                    String visitId = object.optString("VISIT_ID");
                    ticketObject.setVisitId(visitId);

                    String platNo = object.optString("TRUCK");
                    ticketObject.setPlatNo(platNo);

                    String driver = object.optString("DRIVER");
                    ticketObject.setDriverName(driver);

                    String begin = object.optString("BEGIN");
                    ticketObject.setBegin(begin);

                    String end = object.optString("END");
                    ticketObject.setEnd(end);

                    String type = object.optString("TYPE");
                    ticketObject.setType(type);
                    VectorModel.getInstance().setListAssignTicketObjects(ticketObject);
                }
            }

            arrData = jsonObject.optJSONArray("unassign");
            if(arrData != null) {
                VectorModel.getInstance().clearListUnassignTicketObjects();
                for(int i=0;i < arrData.length();i++){
                    JSONObject object = arrData.optJSONObject(i);
                    TicketObject ticketObject = new TicketObject();
                    String visitId = object.optString("VISIT_ID");
                    ticketObject.setVisitId(visitId);

                    String platNo = object.optString("TRUCK");
                    ticketObject.setPlatNo(platNo);

                    String driver = object.optString("DRIVER");
                    ticketObject.setDriverName(driver);

                    String begin = object.optString("BEGIN");
                    ticketObject.setBegin(begin);

                    String end = object.optString("END");
                    ticketObject.setEnd(end);

                    String type = object.optString("TYPE");
                    ticketObject.setType(type);
                    VectorModel.getInstance().setListUnassignTicketObjects(ticketObject);
                }
            }

        } catch (JSONException e) {
            setError(e.getMessage());
        }

    }
}
