package com.ikt.main.to.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ikt.main.to.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifins on 9/24/16.
 */

public class CheckingVinOrTripActivity extends AppCompatActivity implements View.OnClickListener{

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
    private String driver, driverId, platNo, title;
    private String loading;
    private String unloading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outgoing_layout);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            title = bundle.getString("title");
            loading = bundle.getString("loading");
            unloading = bundle.getString("unloading");
        }
        setToolBar();
        btnTripNo.setOnClickListener(this);
        btnVinsNo.setOnClickListener(this);
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
        toolbarTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        if(btnVinsNo == v){
            Intent i = new Intent(this, InputVinActivity.class);
            i.putExtra("title", title);
            i.putExtra("type", type);
            i.putExtra("driver", driver);
            i.putExtra("driverId", driverId);
            i.putExtra("platNo", platNo);
            i.putExtra("proses", proses);
            i.putExtra("loading", loading);
            i.putExtra("unloading", unloading);
            startActivity(i);
        }else if(btnTripNo == v){
            Intent i = new Intent(this, InputTripNoActivity.class);
            i.putExtra("title", title);
            i.putExtra("type", type);
            i.putExtra("driver", driver);
            i.putExtra("driverId", driverId);
            i.putExtra("platNo", platNo);
            i.putExtra("proses", proses);
            i.putExtra("loading", loading);
            i.putExtra("unloading", unloading);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
