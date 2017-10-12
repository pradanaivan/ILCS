package com.ikt.main.to.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/12/16.
 */
public class LanguageActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.radioInd)
    AppCompatRadioButton radioInd;
    @Bind(R.id.radioEng)
    AppCompatRadioButton radioEng;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_layout);
        ButterKnife.bind(this);
        setToolBar();
        radioInd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean checked) {
                if (checked) {
                    PreferenceManagers.setDataWithSameKey("in", Utility.SELECTED_LANGUAGE, LanguageActivity.this);
                    radioEng.setChecked(false);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        radioEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean checked) {
                if (checked) {
                    PreferenceManagers.setDataWithSameKey("en", Utility.SELECTED_LANGUAGE, LanguageActivity.this);
                    radioInd.setChecked(false);
                    setResult(RESULT_OK);
                    finish();
                }
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
        toolbarTitle.setText(getResources().getString(R.string.action_language));
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
