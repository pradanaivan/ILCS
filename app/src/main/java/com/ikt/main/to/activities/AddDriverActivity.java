package com.ikt.main.to.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ListArrayAdapter;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.parser.AddDriverParser;
import com.ikt.main.to.parser.SimpleParser;
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
 * Created by Arifin on 2/8/16.
 */
public class AddDriverActivity extends BaseActivity2 implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, IHttpResponse,TapView {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtUsername)
    MaterialEditText edtUsername;
    @Bind(R.id.edtName)
    MaterialEditText edtName;
    @Bind(R.id.edtPhone)
    MaterialEditText edtPhone;
    @Bind(R.id.edtKTP)
    MaterialEditText edtKTP;
    @Bind(R.id.edtSIM)
    MaterialEditText edtSIM;
    @Bind(R.id.edtNewPassword)
    MaterialEditText edtNewPassword;
    @Bind(R.id.cbShowNewPwd)
    CheckBox cbShowNewPwd;
    @Bind(R.id.edtConfNewPassword)
    MaterialEditText edtConfNewPassword;
    @Bind(R.id.cbShowConfPwd)
    CheckBox cbShowConfPwd;
    @Bind(R.id.spinSim)
    Spinner spinSim;
    @Bind(R.id.llSpin)
    LinearLayout llSpin;
    @Bind(R.id.btnAddDriver)
    Button btnAddDriver;
    @Bind(R.id.btnCancel)
    Button btnCancel;

    private ActionBar mActionBar;
    private String orgId, sesssion, userId;
    private ArrayList<String> titleSim, idSim;
    private DriverObject driverData;
    private String lastId;
    // Satrio
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_driver_layout);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            driverData = (DriverObject) bundle.getSerializable("data");
        }
        setToolBar();
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        sesssion = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        lastId = PreferenceManagers.getData(Config.KEY_LAST_ID, this);
        setSim();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddDriver.setOnClickListener(this);
        cbShowNewPwd.setOnCheckedChangeListener(this);
        cbShowConfPwd.setOnCheckedChangeListener(this);
        if (driverData != null) {
            edtUsername.setText(driverData.getUsername());
            edtUsername.setEnabled(false);
            edtName.setText(driverData.getDriverName());
            edtSIM.setText(driverData.getLicenseNo());
            edtPhone.setText(driverData.getDriverPhone());
            edtKTP.setText(driverData.getIdentityNo());
            btnAddDriver.setText(getResources().getString(R.string.edit_driver));
        }
    }

    private void setSim() {
        titleSim = new ArrayList<String>();
        idSim = new ArrayList<String>();
        Resources res = getResources();
//        titleSim.add(res.getString(R.string.choose_license_type));
//        idSim.add("0");

        titleSim.add("A");
        idSim.add("A");
        titleSim.add("B1");
        idSim.add("B1");
        titleSim.add("B2");
        idSim.add("B2");
        titleSim.add("C");
        idSim.add("C");

        ListArrayAdapter adapter = new ListArrayAdapter(this, titleSim, idSim);
        spinSim.setAdapter(adapter);
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.back_arrow);
            upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            mActionBar.setHomeAsUpIndicator(upArrow);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setTitle("");
        }
        if (driverData != null) {
            toolbarTitle.setText(getResources().getString(R.string.edit_driver));
        }else{
            toolbarTitle.setText(getResources().getString(R.string.add_driver));
        }
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == cbShowNewPwd) {
            if (!isChecked) {
                // show password
                edtNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // hide password
                edtNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        } else if (buttonView == cbShowConfPwd) {
            if (!isChecked) {
                // show password
                edtConfNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // hide password
                edtConfNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }

    private void createDriver() {
        HttpTask task = new HttpTask(this, Config.URL_ADD_DRIVER, 1001, Config.POST, this, AddDriverParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("org_id", orgId));
        post.add(new BasicNameValuePair("user_id", userId));
        post.add(new BasicNameValuePair("session_id", sesssion));
        post.add(new BasicNameValuePair(Config.KEY_LAST_ID, lastId + ""));
        post.add(new BasicNameValuePair("name", edtName.getText().toString()));
        post.add(new BasicNameValuePair("username", edtUsername.getText().toString()));
        String pwd = Utility.getMD5("2"+ edtUsername.getText().toString()+ edtNewPassword.getText().toString());
        post.add(new BasicNameValuePair("password", pwd));
        post.add(new BasicNameValuePair("phone", edtPhone.getText().toString()));
        post.add(new BasicNameValuePair("ktp", edtKTP.getText().toString()));
        post.add(new BasicNameValuePair("sim", edtSIM.getText().toString()));
        int index = spinSim.getSelectedItemPosition();
        String idSimSelected = idSim.get(index);
        post.add(new BasicNameValuePair("gol_sim", idSimSelected));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void editDriver() {
        HttpTask task = new HttpTask(this, Config.URL_EDIT_DRIVER, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("org_id", orgId));
        post.add(new BasicNameValuePair("user_id", userId));
        post.add(new BasicNameValuePair("driver_id", driverData.getDriverId()));
        post.add(new BasicNameValuePair("session_id", sesssion));
        post.add(new BasicNameValuePair(Config.KEY_LAST_ID, lastId + ""));
        post.add(new BasicNameValuePair("name", edtName.getText().toString()));
        post.add(new BasicNameValuePair("username", edtUsername.getText().toString()));
        if(!edtNewPassword.getText().toString().isEmpty()) {
            String pwd = Utility.getMD5(edtUsername.getText().toString() + edtNewPassword.getText().toString() + "2");
            post.add(new BasicNameValuePair("password", pwd));
        }
        post.add(new BasicNameValuePair("phone", edtPhone.getText().toString()));
        post.add(new BasicNameValuePair("ktp", edtKTP.getText().toString()));
        post.add(new BasicNameValuePair("sim", edtSIM.getText().toString()));
        int index = spinSim.getSelectedItemPosition();
        String idSimSelected = idSim.get(index);
        post.add(new BasicNameValuePair("gol_sim", idSimSelected));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddDriver) {
            if (driverData != null) {
                if (    ( edtKTP.getText().toString().isEmpty() && edtSIM.getText().toString().isEmpty())
                        || edtName.getText().toString().isEmpty()
                        || edtPhone.getText().toString().isEmpty()

                        ) {
                    Utility.validateEditText(edtKTP, getResources().getString(R.string.mandatory));
                    Utility.validateEditText(edtName, getResources().getString(R.string.mandatory));
                    Utility.validateEditText(edtPhone, getResources().getString(R.string.mandatory));
                    Utility.validateEditText(edtSIM, getResources().getString(R.string.mandatory));

                } else {
                    if (edtNewPassword.getText().toString().equals(edtConfNewPassword.getText().toString())) {
                        Utility.removeErrorEditText(edtUsername);
                        Utility.removeErrorEditText(edtName);
                        Utility.removeErrorEditText(edtNewPassword);
                        Utility.removeErrorEditText(edtConfNewPassword);
                        Utility.removeErrorEditText(edtPhone);
                        Utility.removeErrorEditText(edtKTP);
                        Utility.removeErrorEditText(edtSIM);
                        editDriver();
                    } else {
                        Utility.validateEditText(edtConfNewPassword, getResources().getString(R.string.not_match));
                    }
                }
            } else if (edtConfNewPassword.getText().toString().isEmpty()
                    || (edtKTP.getText().toString().isEmpty() && edtSIM.getText().toString().isEmpty())
                    || edtName.getText().toString().isEmpty()
                    || edtNewPassword.getText().toString().isEmpty()
                    || edtPhone.getText().toString().isEmpty()
                    || edtUsername.getText().toString().isEmpty()) {
                Utility.validateEditText(edtConfNewPassword, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtKTP, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtName, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtNewPassword, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtPhone, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtSIM, getResources().getString(R.string.mandatory));
                Utility.validateEditText(edtUsername, getResources().getString(R.string.mandatory));

            } else {
                if (edtNewPassword.getText().toString().equals(edtConfNewPassword.getText().toString())) {
                    Utility.removeErrorEditText(edtUsername);
                    Utility.removeErrorEditText(edtName);
                    Utility.removeErrorEditText(edtNewPassword);
                    Utility.removeErrorEditText(edtConfNewPassword);
                    Utility.removeErrorEditText(edtPhone);
                    Utility.removeErrorEditText(edtKTP);
                    Utility.removeErrorEditText(edtSIM);
                    createDriver();
                } else {
                    Utility.validateEditText(edtConfNewPassword, getResources().getString(R.string.not_match));
                }
            }
        }
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(this);
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        final String add = "Successfully Added Driver";
        final String edit = "Successfully Edit Driver";
        if (result instanceof AddDriverParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                if(driverData == null) {
//                    Utility.createDialogInfo(this, "Successfully added driver", this);
                    // Satrio
                    dialogs(add, "", "");
                }
            }
        }else if(result instanceof SimpleParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                DBHelper dbHelper = new DBHelper(AddDriverActivity.this);
                driverData.setDriverName(edtName.getText().toString().trim());
                driverData.setDriverPhone(edtPhone.getText().toString().trim());
                driverData.setIdentityNo(edtKTP.getText().toString().trim());
                driverData.setLicenseNo(edtSIM.getText().toString());
                dbHelper.updateDriver(driverData);
//                Utility.createDialogInfo(this, "Successfully edit driver", this);
                // Satrio
                dialogs(edit, "", "");
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
        tvDialog.setText(info1);
        final TextView tvTitle1 = (TextView) dialog.findViewById(R.id.tvTitle1);
        tvTitle1.setVisibility(View.GONE);
        final TextView tvTitle2 = (TextView) dialog.findViewById(R.id.tvTitle2);
        tvTitle2.setVisibility(View.GONE);
        final TextView tvTitle3 = (TextView) dialog.findViewById(R.id.tvTitle3);
        tvTitle3.setVisibility(View.GONE);
        final TextView tvInfo1 = (TextView) dialog.findViewById(R.id.tvInfo1);
        tvInfo1.setVisibility(View.GONE);
        final TextView tvInfo2 = (TextView) dialog.findViewById(R.id.tvInfo2);
        tvInfo2.setVisibility(View.GONE);
        final TextView tvInfo3 = (TextView) dialog.findViewById(R.id.tvInfo3);
        tvInfo3.setVisibility(View.GONE);
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
        if (parser.isError()) {
            Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompleted(Context context, int pid) {
    }

    @Override
    public void setAction(int type, int position, String name) {
        if(type == 0 && name.equalsIgnoreCase("info")){
            finish();
        }else{
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("pos", 3);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }
}
