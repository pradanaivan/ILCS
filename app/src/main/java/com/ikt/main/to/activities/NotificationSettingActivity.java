package com.ikt.main.to.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ikt.main.to.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/12/16.
 */
public class NotificationSettingActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.chkAssignTicket)
    CheckBox chkAssignTicket;
    @Bind(R.id.chkUnassignTicket)
    CheckBox chkUnassignTicket;
    @Bind(R.id.chkStatusTruckUpdate)
    CheckBox chkStatusTruckUpdate;
    @Bind(R.id.chkStatusOperation)
    CheckBox chkStatusOperation;
    @Bind(R.id.chkStatusComplete)
    CheckBox chkStatusComplete;
    @Bind(R.id.chkStatusLeft)
    CheckBox chkStatusLeft;
    @Bind(R.id.chkInboxSetting)
    CheckBox chkInboxSetting;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_setting_layout);
        ButterKnife.bind(this);
        setToolBar();
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
        toolbarTitle.setText(getResources().getString(R.string.action_notif));
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
}
