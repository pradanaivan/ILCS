package com.ikt.main.to.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arifin on 3/12/16.
 */
public class MyAccountActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtName)
    TextView txtName;
    @Bind(R.id.txtUsername)
    TextView txtUsername;
    @Bind(R.id.txtCompany)
    TextView txtCompany;
    @Bind(R.id.edtOldPassword)
    MaterialEditText edtOldPassword;
    @Bind(R.id.cbShowPwd)
    CheckBox cbShowPwd;
    @Bind(R.id.edtNewPassword)
    MaterialEditText edtNewPassword;
    @Bind(R.id.cbShowNewPwd)
    CheckBox cbShowNewPwd;
    @Bind(R.id.edtConfNewPassword)
    MaterialEditText edtConfNewPassword;
    @Bind(R.id.cbShowConfPwd)
    CheckBox cbShowConfPwd;
    @Bind(R.id.btnChangePwd)
    Button btnChangePwd;
    @Bind(R.id.btnBack)
    Button btnBack;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_layout);
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
        toolbarTitle.setText(getResources().getString(R.string.my_account));
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

    @OnClick(R.id.btnBack)
    public void onClick() {

    }
}
