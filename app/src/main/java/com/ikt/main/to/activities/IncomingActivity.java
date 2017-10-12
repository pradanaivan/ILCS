package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.parser.SimpleParser;
import com.ikt.main.to.parser.VinParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/14/16.
 */
public class IncomingActivity extends BaseActivity implements View.OnClickListener,IHttpResponse {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtAmountOfVins)
    MaterialEditText edtAmountOfVins;
    @Bind(R.id.edtTripNo)
    MaterialEditText edtTripNo;
    @Bind(R.id.spinTime)
    Spinner spinTime;
    @Bind(R.id.llSpin)
    LinearLayout llSpin;
    @Bind(R.id.txtTimeSlot)
    TextView txtTimeSlot;
    @Bind(R.id.timeSlotBtn)
    LinearLayout timeSlotBtn;
    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.btnNext)
    Button btnNext;

    private ActionBar mActionBar;
    private List<String> dataTime, idTime;
    private int type, proses;
    private String driver, driverId, platNo;
    private TimeSlotObject obj;
    private String orgId, session, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incoming_announcement_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
        }
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        timeSlotBtn.setOnClickListener(this);
        edtAmountOfVins.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
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
        toolbarTitle.setText(getResources().getString(R.string.incoming));
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
    public void onClick(View v) {
        if (v == timeSlotBtn) {
            Intent i = new Intent(this, TimeSlotActivity.class);
            i.putExtra("type", type);
            startActivityForResult(i, 100);
        } else if (v == btnNext) {
            String vins = edtAmountOfVins.getText().toString();
            String tripKapal = edtTripNo.getText().toString();

            if (vins.isEmpty()) {
                Utility.validateEditText(edtAmountOfVins, getResources().getString(R.string.mandatory));
            } else {
                if (obj != null) {
                    if (proses == 2) {
                        Intent i = new Intent(this, OutgoingActivity.class);
                        i.putExtra("type", type);
                        i.putExtra("driver", driver);
                        i.putExtra("driverId", driverId);
                        i.putExtra("platNo", platNo);
                        i.putExtra("proses", proses);
                        i.putExtra("timeSlot", obj);
                        i.putExtra("vinsAmount", vins);
                        i.putExtra("tripNo", tripKapal);
                        startActivity(i);
                    } else {
                        checkVin();

                    }

                } else {
                    Toast.makeText(this, getResources().getString(R.string.choose_time_slot), Toast.LENGTH_SHORT).show();
                }
            }
        }else if(v == edtAmountOfVins){
            Intent i = new Intent(this, InputVinActivity.class);
            i.putExtra("title", getResources().getString(R.string.incoming));
            i.putExtra("isIncoming", true);
            startActivityForResult(i, 201);
        }
    }

    private void checkVin(){
        HttpTask task = new HttpTask(this, Config.URL_CHECK_VIN, 1001, Config.POST, this, VinParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair("trip", edtTripNo.getText().toString()));
        post.add(new BasicNameValuePair("incoming", "1"));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bundle arguments = data.getExtras();
                obj = (TimeSlotObject) arguments.getSerializable("data");
                txtTimeSlot.setText(obj.getTimeSlotBookDate() + " - " + obj.getTimeSlotCode() + " - " + obj.getTimeSlotPosition());
            }
        }else if(requestCode == 201){
            if(resultCode == RESULT_OK){
                Bundle arguments = data.getExtras();
                HashMap<String, String> map = (HashMap<String, String>) arguments.getSerializable("data");
                edtAmountOfVins.setText(String.valueOf(map.size()));
            }
        }

    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof SimpleParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                String vins = edtAmountOfVins.getText().toString();
                String tripKapal = edtTripNo.getText().toString();
                Intent i = new Intent(this, ConfirmAnnouncementActivity.class);
                i.putExtra("type", type);
                i.putExtra("driver", driver);
                i.putExtra("driverId", driverId);
                i.putExtra("platNo", platNo);
                i.putExtra("proses", proses);
                i.putExtra("timeSlot", obj);
                i.putExtra("vinsAmount", vins);
                i.putExtra("tripNo", tripKapal);
                startActivity(i);
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
}
