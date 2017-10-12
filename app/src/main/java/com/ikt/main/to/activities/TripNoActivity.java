package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ListArrayAdapter;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.ObjectNewSlot;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.parser.SlotParser;
import com.ikt.main.to.parser.SlotParserOut;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/14/16.
 */
public class TripNoActivity extends BaseActivity2 implements View.OnClickListener,IHttpResponse,AdapterView.OnItemSelectedListener {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtNoOfTrip)
    MaterialEditText edtNoOfTrip;
    @Bind(R.id.edtTruckCapacity)
    MaterialEditText edtTruckCapacity;
    @Bind(R.id.spinTime)
    Spinner spinTime;
    @Bind(R.id.llSpin)
    LinearLayout llSpin;
    @Bind(R.id.txtTimeSlot)
    TextView txtTimeSlot;
    @Bind(R.id.timeSlotBtn)
    LinearLayout timeSlotBtn;
    @Bind(R.id.btnNext)
    Button btnNext;
    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.spinDate)
    Spinner spinDate;
    @Bind(R.id.spinSlot)
    Spinner spinSlot;
    @Bind(R.id.spinArea)
    Spinner spinArea;

    private ActionBar mActionBar;
    private List<String> dataTime, idTime;
    private int type, proses;
    private String driver, driverId, platNo, outType, vinsAmount, tripKapal, vinIn;
    private TimeSlotObject obj, timeSlotObject;
    ListArrayAdapter adapterDate;
    ListArrayAdapter adapterSlot;
    ListArrayAdapter adapterArea;
    private List<String> arrDate = new ArrayList<String>();
    private List<String> arrSlot = new ArrayList<String>();
    private List<String> arrArea = new ArrayList<String>();
    private List<String> arrSlotTemp = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_no_input_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            outType = bundle.getString("outType");
            if (proses == 2) {
                timeSlotObject = (TimeSlotObject) bundle.getSerializable("timeSlot");
                vinsAmount = bundle.getString("vinsAmount");
                tripKapal = bundle.getString("tripNo");
                vinIn = bundle.getString("vinIn");

            }
        }
        edtNoOfTrip.setOnClickListener(this);
        timeSlotBtn.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapterDate = new ListArrayAdapter(this, arrDate, arrDate);
        spinDate.setAdapter(adapterDate);
        spinDate.setOnItemSelectedListener(this);
        adapterSlot = new ListArrayAdapter(this, arrSlot, arrSlot);
        spinSlot.setAdapter(adapterSlot);
        adapterArea = new ListArrayAdapter(this, arrArea, arrArea);
        spinArea.setAdapter(adapterArea);
        getSlot();
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
        toolbarTitle.setText(getResources().getString(R.string.outgoing));
    }

    private void getSlot() {
        HttpTask task = new HttpTask(this, Config.URL_GET_SLOT, 1001, Config.POST, this, SlotParserOut.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("incoming", "0"));
        post.add(new BasicNameValuePair("terminal", type == 0 ? "international" : "domestic"));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, PreferenceManagers.getData(Config.KEY_USER_ID, this)));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, PreferenceManagers.getData(Config.KEY_SESSION, this)));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
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
        if (v == timeSlotBtn) {
            Intent i = new Intent(this, TimeSlotActivity.class);
            i.putExtra("type", type);
            if (timeSlotObject != null)
                i.putExtra("nr", timeSlotObject.getTimeSlotNR());
            startActivityForResult(i, 100);
        } else if (v == btnNext) {
            String tripNo = edtNoOfTrip.getText().toString();
            String capacity = edtTruckCapacity.getText().toString();
            if (capacity.length() > 0 && Integer.parseInt(capacity) > 0) {

                String area = arrArea.get(spinArea.getSelectedItemPosition());
                String date = arrDate.get(spinDate.getSelectedItemPosition());
                String slot = arrSlot.get(spinSlot.getSelectedItemPosition());
                ObjectNewSlot objectNewSlot = new ObjectNewSlot();
                objectNewSlot.setArea_out(area);
                objectNewSlot.setDate_out(date);
                objectNewSlot.setSlot_out(slot);
                VectorModel.getInstance().clearObjectNewSlotOut();
                VectorModel.getInstance().setObjectNewSlotsOut(objectNewSlot);

                Intent i = new Intent(this, ConfirmAnnouncementActivity.class);
                i.putExtra("type", type);
                i.putExtra("driver", driver);
                i.putExtra("driverId", driverId);
                i.putExtra("platNo", platNo);
                i.putExtra("proses", proses);
                i.putExtra("timeSlot", obj);
                i.putExtra("outType", outType);
                i.putExtra("capacity", capacity);
                i.putExtra("tripNo", tripNo);
                if (proses == 2) {
                    i.putExtra("timeSlotOut", timeSlotObject);
                    i.putExtra("vinsUnload", vinsAmount);
                    i.putExtra("tripKapal", tripKapal);
                    i.putExtra("vinIn", vinIn);
                }
                startActivity(i);
            } else {
//                Utility.validateEditText(edtNoOfTrip, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtTruckCapacity, getResources().getString(R.string.mandatory));
//                Snackbar.make(btnBack, getResources().getString(R.string.choose_time_slot), Snackbar.LENGTH_SHORT).show();
            }
        } else if (v == edtNoOfTrip) {
            Intent i = new Intent(this, SearchTripActivity.class);
            i.putExtra("jenis", "outgoing");
            startActivityForResult(i, 200);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Bundle arguments = data.getExtras();
                obj = (TimeSlotObject) arguments.getSerializable("data");
                txtTimeSlot.setText(obj.getTimeSlotBookDate() + " - " + obj.getTimeSlotCode() + " - " + obj.getTimeSlotPosition());
            } else if (requestCode == 200) {
                Bundle arguments = data.getExtras();
                String trip = (String) arguments.getSerializable("data");
                edtNoOfTrip.setText(trip);
            }
        }

    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof SlotParserOut){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                try {
                    ArrayList<String> arrDateTmp = VectorModel.getInstance().getObjectNewSlotsDateOut();
                    for (int i = 0; i < arrDateTmp.size(); i++) {
                        arrDate.add(arrDateTmp.get(i));
                    }
                    arrSlotTemp = VectorModel.getInstance().getObjectNewSlotsSlotOut();

                    String tmpArr = arrSlotTemp.get(0);
                    JSONArray array = new JSONArray(tmpArr);
                    for (int i = 0; i < array.length(); i++) {
                        arrSlot.add(array.optString(i));
                    }
                    ArrayList<String> arrAreaTmp = VectorModel.getInstance().getObjectNewSlotsAreaOut();
                    for (int i = 0; i < arrAreaTmp.size(); i++) {
                        arrArea.add(arrAreaTmp.get(i));
                    }
                    adapterArea.notifyDataSetChanged();
                    adapterSlot.notifyDataSetChanged();
                    adapterDate.notifyDataSetChanged();

//                    spinDate.setAdapter(adapterDate);
//                    spinArea.setAdapter(adapterArea);
//                    spinSlot.setAdapter(adapterSlot);
                }catch (JSONException e){

                }
            }
        }
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        try {
            int index = spinDate.getSelectedItemPosition();
            if(arrSlotTemp.size() > 0) {
                String tmpArr = arrSlotTemp.get(index);
                JSONArray array = new JSONArray(tmpArr);
                arrSlot.clear();
                for (int i = 0; i < array.length(); i++) {
                    arrSlot.add(array.optString(i));
                }
                adapterSlot.notifyDataSetChanged();
                spinSlot.setAdapter(adapterSlot);
            }
        }catch (JSONException e){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
