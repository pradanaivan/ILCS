package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TicketObjectDetail;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 4/10/16.
 */
public class TicketObjectDetailParser extends DefaultErrorModel implements IParser{

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
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
//            "data": [{
//                "CARRIER": "PT. DUNIA EXPRESS TRANSINDO",
//                        "DRIVER": "dfg", DONE
//                        "TRUCK": "B9003WV",
//                        "OUTGOING_VOYAGE_NR": null,
//                        "OUTGOING_VESSEL": null,
//                        "TIME_BEGIN": "30-MAR-2016 13:00",
//                        "TIME_END": "30-MAR-2016 17:00",
//                        "LOADAREA_IN": "CD005",
//                        "LOADAREA_OUT": null,
//                        "AMOUNT_CAR_PICKUP": "0",
//                        "AMOUNT_CAR_VESSEL": "9"
//            }],
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arrData = jsonObject.optJSONArray("data");
            if(arrData != null){
                VectorModel.getInstance().clearTicketObjectDetails();
                for (int i=0;i < arrData.length();i++){
                    JSONObject object = arrData.optJSONObject(i);
                    TicketObjectDetail objectDetail = new TicketObjectDetail();
                    objectDetail.setDriverName(object.optString("DRIVER"));
                    objectDetail.setEnd(object.optString("TIME_END"));
                    objectDetail.setAmountCarPickup(object.optString("AMOUNT_CAR_PICKUP"));
                    objectDetail.setBegin(object.optString("TIME_BEGIN"));
                    objectDetail.setAmountCarVessel(object.optString("AMOUNT_CAR_VESSEL"));
                    objectDetail.setAreaIn(object.optString("LOADAREA_IN"));
                    objectDetail.setAreaOut(object.optString("LOADAREA_OUT"));
                    objectDetail.setCarrier(object.optString("CARRIER"));
                    objectDetail.setOutGoingVessel(object.optString("OUTGOING_VESSEL"));
                    objectDetail.setOutGoingVoyage(object.optString("OUTGOING_VOYAGE_NR"));
                    objectDetail.setPlatNo(object.optString("TRUCK"));
                    VectorModel.getInstance().setTicketObjectDetails(objectDetail);
                }
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
