package com.ikt.main.to.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.object.TimeSlotObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/14/16.
 */
public class OutgoingActivity extends BaseActivity2 implements View.OnClickListener {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btnVinsNo)
    Button btnVinsNo;
    @Bind(R.id.btnTripNo)
    Button btnTripNo;

    private ActionBar mActionBar;
    private int type, proses;
    private String driver, driverId, platNo, vinsAmount, tripKapal, vinIn;
    private TimeSlotObject timeSlotObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outgoing_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            if (proses == 2) {
                timeSlotObject = (TimeSlotObject) bundle.getSerializable("timeSlot");
                vinsAmount = bundle.getString("vinsAmount");
                tripKapal = bundle.getString("tripNo");
                vinIn = bundle.getString("vin");
            }
        }
        btnVinsNo.setOnClickListener(this);
        btnTripNo.setOnClickListener(this);

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
        if (v == btnTripNo) {
            Intent i = new Intent(this, TripNoActivity.class);
            i.putExtra("type", type);
            i.putExtra("driver", driver);
            i.putExtra("driverId", driverId);
            i.putExtra("platNo", platNo);
            i.putExtra("proses", proses);
            i.putExtra("outType", "trip");
            if (proses == 2) {
                i.putExtra("timeSlot", timeSlotObject);
                i.putExtra("vinsAmount", vinsAmount);
                i.putExtra("tripNo", tripKapal);
                i.putExtra("vinIn", vinIn);
            }
            startActivity(i);
        } else if (v == btnVinsNo) {
            Intent i = new Intent(this, OutgoingVinsNoActivity.class);
            i.putExtra("type", type);
            i.putExtra("driver", driver);
            i.putExtra("driverId", driverId);
            i.putExtra("platNo", platNo);
            i.putExtra("proses", proses);
            i.putExtra("outType", "vins");
            if (proses == 2) {
                i.putExtra("timeSlot", timeSlotObject);
                i.putExtra("vinsAmount", vinsAmount);
                i.putExtra("tripNo", tripKapal);
                i.putExtra("vinIn", vinIn);
            }
            startActivity(i);
        }
    }
}
