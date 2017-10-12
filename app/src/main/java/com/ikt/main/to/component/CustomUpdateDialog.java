package com.ikt.main.to.component;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ardan on 3/13/17.
 */

public class CustomUpdateDialog extends Dialog implements View.OnClickListener {

    private final int status;
    private final TapView listener;
    public Activity c;
    public Dialog d;
    @Bind(R.id.txt_dia)
    TextView txtDia;
    @Bind(R.id.btn_yes)
    Button btnYes;
    @Bind(R.id.btn_no)
    Button btnNo;
    @Bind(R.id.layoutYesNo)
    LinearLayout layoutYesNo;
    @Bind(R.id.btn_update)
    Button btnUpdate;
    @Bind(R.id.layoutUpdate)
    LinearLayout layoutUpdate;


    public CustomUpdateDialog(Activity a, int status, TapView listener) {
        super(a);
        this.c = a;
        this.status = status;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_layout);
        ButterKnife.bind(this);
        if (status == 1) {
            layoutYesNo.setVisibility(View.GONE);
            layoutUpdate.setVisibility(View.VISIBLE);
            btnUpdate.setOnClickListener(this);
        } else {
            layoutYesNo.setVisibility(View.VISIBLE);
            layoutUpdate.setVisibility(View.GONE);
            btnNo.setOnClickListener(this);
            btnYes.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                openPlayStore();
                break;
            case R.id.btn_no:
                listener.setAction(status, status, "no");
                break;
            case R.id.btn_update:
                openPlayStore();
                break;
        }
        dismiss();
    }

    private void openPlayStore() {
        final String appPackageName = c.getPackageName();
        try {
            c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        c.finish();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(false);
    }
}