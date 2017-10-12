package com.ikt.main.to.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.IncomingObject;
import com.ikt.main.to.object.ObjectNewSlot;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.parser.IncomingParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/19/16.
 */
public class ConfirmAnnouncementActivity extends BaseActivity2 implements View.OnClickListener, IHttpResponse, TapView {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtDriverName)
    TextView txtDriverName;
    @Bind(R.id.txtTruckPlateNo)
    TextView txtTruckPlateNo;
    @Bind(R.id.txtIncomingCapacityVins)
    TextView txtIncomingCapacityVins;
    @Bind(R.id.txtOutgoingCapacityVins)
    TextView txtOutgoingCapacityVins;
    @Bind(R.id.btnConfirm)
    Button btnConfirm;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.txtTimeSlotIn)
    TextView txtTimeSlotIn;
    @Bind(R.id.txtTimeSlotOut)
    TextView txtTimeSlotOut;

    private ActionBar mActionBar;
    private int type, proses;
    private String driver, driverId, platNo, vinsAmount, tripNo, outType, capacity, vin, vinsUnload, tripKapal, vinIn;
    private TimeSlotObject timeSlotObject, timeSlotOutObject;
    private ObjectNewSlot objectNewSlot = null;
    private ObjectNewSlot objectNewSlotOut = null;
    // Satrio
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_announcement_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            vinsAmount = bundle.getString("vinsAmount", "");
            tripNo = bundle.getString("tripNo");
            timeSlotObject = (TimeSlotObject) bundle.getSerializable("timeSlot");
            outType = bundle.getString("outType");
            capacity = bundle.getString("capacity");
            vin = bundle.getString("vin", "");
            if (proses == 2) {
                vinsUnload = bundle.getString("vinsUnload");
                vinsAmount = bundle.getString("vinsUnload");
                tripKapal = bundle.getString("tripKapal");
                timeSlotOutObject = (TimeSlotObject) bundle.getSerializable("timeSlotOut");
                vinIn = bundle.getString("vinIn");
            }
        }
        String privilege = PreferenceManagers.getData(Config.KEY_PRIVILEGE, this);
        int privilegeInt = Integer.parseInt(privilege);
        ArrayList<ObjectNewSlot> slotArrayList = VectorModel.getInstance().getObjectNewSlotsIn();
        if (slotArrayList != null && slotArrayList.size() > 0) objectNewSlot = slotArrayList.get(0);

        ArrayList<ObjectNewSlot> slotOutArrayList = VectorModel.getInstance().getObjectNewSlotsOut();
        if (slotOutArrayList != null && slotOutArrayList.size() > 0)
            objectNewSlotOut = slotOutArrayList.get(0);

        txtDriverName.setText(driver);
//        txtAmountOfVin.setText(getResources().getText(R.string.amount_of_vins) + " : " + vinsAmount);


        if (timeSlotObject != null && proses == 0) {
            txtIncomingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Incoming : " + vinsAmount);
            txtIncomingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setVisibility(View.GONE);
            txtTimeSlotIn.setText("Incoming : " + timeSlotObject.getTimeSlotBookDate() + " / " + timeSlotObject.getTimeSlotCode() + " / " + timeSlotObject.getTimeSlotPosition());
        }if (timeSlotObject != null && proses == 1) {
            txtOutgoingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setText(capacity);
            txtIncomingCapacityVins.setVisibility(View.GONE);
            txtTimeSlotIn.setText("Outgoing : " + timeSlotObject.getTimeSlotBookDate() + " / " + timeSlotObject.getTimeSlotCode() + " / " + timeSlotObject.getTimeSlotPosition());
        }if (timeSlotOutObject != null) {
            txtIncomingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " : " + vinsAmount);
            txtIncomingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setText(capacity);
            txtTimeSlotOut.setText("Outgoing : " + timeSlotOutObject.getTimeSlotBookDate() + " / " + timeSlotOutObject.getTimeSlotCode() + " / " + timeSlotOutObject.getTimeSlotPosition());
        }
        if (objectNewSlot != null && proses == 0) {
            txtIncomingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Incoming : " + vinsAmount);
            txtIncomingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setVisibility(View.GONE);
            txtTimeSlotOut.setVisibility(View.GONE);
            txtTimeSlotIn.setText("Incoming : " + objectNewSlot.getDate_in() + " / " + objectNewSlot.getSlot_in() + " / " + objectNewSlot.getArea_in());
        } else if (objectNewSlotOut != null && proses == 1) {
            txtOutgoingCapacityVins.setVisibility(View.VISIBLE);
            txtIncomingCapacityVins.setVisibility(View.GONE);
            if(outType.equalsIgnoreCase("trip"))
                txtOutgoingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Outgoing : " + capacity);
            else
                txtOutgoingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Outgoing : " + vinsAmount);
            txtTimeSlotOut.setVisibility(View.GONE);
            txtTimeSlotIn.setText("Outgoing : " + objectNewSlotOut.getDate_out() + " / " + objectNewSlotOut.getSlot_out() + " / " + objectNewSlotOut.getArea_out());
        } else if (objectNewSlotOut != null && proses == 2) {
            txtIncomingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Incoming : " + vinsAmount);
            txtIncomingCapacityVins.setVisibility(View.VISIBLE);

            txtOutgoingCapacityVins.setVisibility(View.VISIBLE);
            txtOutgoingCapacityVins.setText(getResources().getText(R.string.amount_of_vins) + " Outgoing : " + capacity);
            txtTimeSlotIn.setText("Incoming : " + objectNewSlot.getDate_in() + " / " + objectNewSlot.getSlot_in() + " / " + objectNewSlot.getArea_in());
            txtTimeSlotOut.setText("Outgoing : " + objectNewSlotOut.getDate_out() + " / " + objectNewSlotOut.getSlot_out() + " / " + objectNewSlotOut.getArea_out());
        }

        txtTruckPlateNo.setText(platNo);

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.back_arrow);
            upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            mActionBar.setHomeAsUpIndicator(upArrow);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setTitle("");
        }
        toolbarTitle.setText(getResources().getString(R.string.confirm_announcement));
    }

    @Override
    public boolean isAvailableSearchBtn() {
        return false;
    }

    @Override
    public boolean isAvailableMyAccBtn() {
        return false;
    }

    @Override
    public boolean isAvailableLangBtn() {
        return false;
    }

    @Override
    public boolean isAvailableNotifBtn() {
        return false;
    }

    @Override
    public boolean isAvailableSignout() {
        return false;
    }

    @Override
    public boolean isAvailableRefreshBtn() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnConfirm) {
            confirmAnnouncement();
        }
    }

    private void confirmAnnouncement() {
        String url = "";
        Class<?> parser = null;
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        String userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        post.add(new BasicNameValuePair("user_id", userId));

//        platNo = platNo.replaceAll("\\s+","");
        switch (proses) {
            case 0:
                url = Config.URL_INCOMING;
                parser = IncomingParser.class;
                post.add(new BasicNameValuePair("plat", platNo));
                post.add(new BasicNameValuePair("driver", driver));
                post.add(new BasicNameValuePair("driver_id", driverId));
                post.add(new BasicNameValuePair("visitDirection", type == 0 ? "international".toUpperCase() : "domestic".toUpperCase()));
                post.add(new BasicNameValuePair("expectedVinsUnloading", vinsAmount));
                post.add(new BasicNameValuePair("vin", vin));
                post.add(new BasicNameValuePair("incoming", true + ""));
//                post.add(new BasicNameValuePair("bookSlot", timeSlotObject.getTimeSlotNR()));
//                post.add(new BasicNameValuePair("startAt", timeSlotObject.getTimeSlotStartAt()));
//                post.add(new BasicNameValuePair("endAt", timeSlotObject.getTimeSlotEndAt()));


                post.add(new BasicNameValuePair("date", objectNewSlot.getDate_in()));
                post.add(new BasicNameValuePair("area", objectNewSlot.getArea_in()));
                post.add(new BasicNameValuePair("slot", objectNewSlot.getSlot_in()));
                break;
            case 1:
                url = Config.URL_OUTGOING;
                parser = IncomingParser.class;
                post.add(new BasicNameValuePair("plat", platNo));
                post.add(new BasicNameValuePair("driver", driver));
                post.add(new BasicNameValuePair("driver_id", driverId));
                post.add(new BasicNameValuePair("visitDirection", type == 0 ? "international".toUpperCase() : "domestic".toUpperCase()));

                if (outType.equalsIgnoreCase("vins")) {
                    post.add(new BasicNameValuePair("tripOrVin", "vin"));
                    post.add(new BasicNameValuePair("vin", vin));
                    post.add(new BasicNameValuePair("capacity", vinsAmount));
                } else if (outType.equalsIgnoreCase("trip")) {
                    post.add(new BasicNameValuePair("tripOrVin", "trip"));
                    post.add(new BasicNameValuePair("tripIncoming", tripNo));
                    post.add(new BasicNameValuePair("capacity", capacity));
                }

                post.add(new BasicNameValuePair("incoming", false + ""));
//                post.add(new BasicNameValuePair("bookSlot", timeSlotObject.getTimeSlotNR()));
//                post.add(new BasicNameValuePair("startAt", timeSlotObject.getTimeSlotStartAt()));
//                post.add(new BasicNameValuePair("endAt", timeSlotObject.getTimeSlotEndAt()));


                post.add(new BasicNameValuePair("date", objectNewSlotOut.getDate_out()));
                post.add(new BasicNameValuePair("area", objectNewSlotOut.getArea_out()));
                post.add(new BasicNameValuePair("slot", objectNewSlotOut.getSlot_out()));

                break;
            case 2:
                url = Config.URL_BACKLOAD;
                parser = IncomingParser.class;
                post.add(new BasicNameValuePair("plat", platNo));
                post.add(new BasicNameValuePair("driver", driver));
                post.add(new BasicNameValuePair("driver_id", driverId));
                post.add(new BasicNameValuePair("visitDirection", type == 0 ? "international".toUpperCase() : "domestic".toUpperCase()));
                post.add(new BasicNameValuePair("vin_in", vinIn));
                if (outType.equalsIgnoreCase("vins")) {
                    post.add(new BasicNameValuePair("tripOrVin", "vin"));
                    post.add(new BasicNameValuePair("vin_out", vin));
                } else if (outType.equalsIgnoreCase("trip")) {
                    post.add(new BasicNameValuePair("tripOrVin", "trip"));
                    post.add(new BasicNameValuePair("tripIncoming", tripNo));
                    post.add(new BasicNameValuePair("capacity", capacity));
                    post.add(new BasicNameValuePair("vin_in", vinIn));
                }

//                String startAt1 = timeSlotObject.getTimeSlotStartAt();
//                String startAt2 = timeSlotOutObject.getTimeSlotStartAt();
//                DateTime dateTimeStart1 = DateTime.parse(startAt1);
//                DateTime dateTimeStart2 = DateTime.parse(startAt2);
//                if (dateTimeStart1.compareTo(dateTimeStart2) > 0) {
//                    post.add(new BasicNameValuePair("startAt", startAt1));
//                } else {
//                    post.add(new BasicNameValuePair("startAt", startAt2));
//                }
//
//                String endAt1 = timeSlotObject.getTimeSlotEndAt();
//                String endAt2 = timeSlotOutObject.getTimeSlotEndAt();
//                DateTime dateTimeEnd1 = DateTime.parse(endAt1);
//                DateTime dateTimeEnd2 = DateTime.parse(endAt2);
//                if (dateTimeEnd1.compareTo(dateTimeEnd2) > 0) {
//                    post.add(new BasicNameValuePair("endAt", endAt1));
//                } else {
//                    post.add(new BasicNameValuePair("endAt", endAt2));
//                }
//                post.add(new BasicNameValuePair("bookSlotIn", timeSlotObject.getTimeSlotNR()));
//                post.add(new BasicNameValuePair("bookSlotOut", timeSlotOutObject.getTimeSlotNR()));
                post.add(new BasicNameValuePair("expectedVinsUnloading", vinsUnload));
                post.add(new BasicNameValuePair("tripKapal", tripKapal));

                objectNewSlotOut = VectorModel.getInstance().getObjectNewSlotsOut().get(0);
                post.add(new BasicNameValuePair("date_out", objectNewSlotOut.getDate_out()));
                post.add(new BasicNameValuePair("area_out", objectNewSlotOut.getArea_out()));
                post.add(new BasicNameValuePair("slot_out", objectNewSlotOut.getSlot_out()));

                objectNewSlot = VectorModel.getInstance().getObjectNewSlotsIn().get(0);
                post.add(new BasicNameValuePair("date_in", objectNewSlot.getDate_in()));
                post.add(new BasicNameValuePair("area_in", objectNewSlot.getArea_in()));
                post.add(new BasicNameValuePair("slot_in", objectNewSlot.getSlot_in()));
                break;
        }
        HttpTask task = new HttpTask(this, url, 1001, Config.POST, this, parser);
        task.setProcessName(getString(R.string.loading_load));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof IncomingParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ArrayList<IncomingObject> arr = VectorModel.getInstance().getIncomingObjects();
                IncomingObject object = arr.get(0);
//                Utility.createDialogInfo(this, "Visit Created\n" + object.getPlatNo() + "\n" + object.getSupir() + "\n" + object.getVisitId(), this);
                // Satrio
                final String info1 = object.getPlatNo()+"";
                final String info2 = object.getSupir()+"";
                final String info3 = object.getVisitId()+"";
                dialogs(info1, info2, info3);
            }
        }
    }

    // Satrio
    private void dialogs(String info1, String info2, String info3){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.d_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button btnConfirm = (Button) dialog.findViewById(R.id.btnNext);
        final TextView tvDialog = (TextView) dialog.findViewById(R.id.tvDialog);
        tvDialog.setText("Visit Created");
        final TextView tvTitle1 = (TextView) dialog.findViewById(R.id.tvTitle1);
        tvTitle1.setText("Truck Plat Number :");
        final TextView tvTitle2 = (TextView) dialog.findViewById(R.id.tvTitle2);
        tvTitle2.setText("Driver Name :");
        final TextView tvTitle3 = (TextView) dialog.findViewById(R.id.tvTitle3);
        tvTitle3.setText("Visit ID :");
        final TextView tvInfo1 = (TextView) dialog.findViewById(R.id.tvInfo1);
        tvInfo1.setText(info1);
        final TextView tvInfo2 = (TextView) dialog.findViewById(R.id.tvInfo2);
        tvInfo2.setText(info2);
        final TextView tvInfo3 = (TextView) dialog.findViewById(R.id.tvInfo3);
        tvInfo3.setText(info3);
        btnConfirm.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                setAction(0, 0, "info");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {

    }

    @Override
    public void setAction(int type, int position, String name) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("pos", 0);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
