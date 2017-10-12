package com.ikt.main.to.activities;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ListArrayAdapter;
import com.ikt.main.to.component.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/14/16.
 */
public class VinsNoActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtNoOfVins)
    MaterialEditText edtNoOfVins;
    @Bind(R.id.spinTime)
    Spinner spinTime;
    @Bind(R.id.llSpin)
    LinearLayout llSpin;

    private ActionBar mActionBar;
    private List<String> dataTime, idTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vins_no_input_layout);
        ButterKnife.bind(this);
        setToolBar();
        setTimeSlot();
    }

    private void setTimeSlot(){
        dataTime = new ArrayList<String>();
        idTime = new ArrayList<String>();
        Resources res = getResources();
        dataTime.add(res.getString(R.string.choose_time_slot));
        idTime.add("0");

        dataTime.add("08:00 - 10:00");
        idTime.add("1");

        dataTime.add("10:00 - 12:00");
        idTime.add("2");

        dataTime.add("12:00 - 14:00");
        idTime.add("3");

        dataTime.add("14:00 - 16:00");
        idTime.add("4");

        dataTime.add("16:00 - 18:00");
        idTime.add("5");

        ListArrayAdapter adapter = new ListArrayAdapter(this, dataTime, idTime);
        spinTime.setAdapter(adapter);
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
        toolbarTitle.setText(getResources().getString(R.string.announcement));
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

    }
}
