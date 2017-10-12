package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.EditTicketObject;
import com.ikt.main.to.object.TruckObject;
import com.ikt.main.to.parser.SaveEditVisitParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arifin on 9/28/16.
 */

public class EditVisitActivity extends AppCompatActivity implements IHttpResponse {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtVisitId)
    TextView txtVisitId;
    @Bind(R.id.txtVisitIdVal)
    TextView txtVisitIdVal;
    @Bind(R.id.txtDriver)
    TextView txtDriver;
    @Bind(R.id.edtDriver)
    MaterialEditText edtDriver;
    @Bind(R.id.txtTruck)
    TextView txtTruck;
    @Bind(R.id.edtTruck)
    MaterialEditText edtTruck;
    @Bind(R.id.txtTerminal)
    TextView txtTerminal;
    @Bind(R.id.edtTerminal)
    MaterialEditText edtTerminal;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.btnSaveAndNext)
    Button btnSaveAndNext;
    @Bind(R.id.rdInternational)
    AppCompatRadioButton rdInternational;
    @Bind(R.id.rdDomestic)
    AppCompatRadioButton rdDomestic;
    @Bind(R.id.rdGroup)
    RadioGroup rdGroup;
    @Bind(R.id.btnVinIncoming)
    Button btnVinIncoming;
    @Bind(R.id.btnVinOrTripOutgoing)
    Button btnVinOrTripOutgoing;

    private ActionBar mActionBar;
    private EditTicketObject editTicketObject;
    private String visitId;
    private String orgId, session, userId, driverId, platNoCode;
    private boolean isSaveAndNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_visit_layout);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            visitId = bundle.getString("visitId");
        }
        setToolBar();
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        ArrayList<EditTicketObject> editTicketObjects = VectorModel.getInstance().getEditTicketObjects();
        editTicketObject = editTicketObjects.get(0);
        edtDriver.setText(editTicketObject.getDriver());
        edtTruck.setText(editTicketObject.getLicencePlate());
        txtVisitIdVal.setText(visitId);
//        edtTerminal.setText(editTicketObject.getTerminal());
        if ("international".equalsIgnoreCase(editTicketObject.getTerminal())) {
            rdInternational.setChecked(true);
        } else if ("domestic".equalsIgnoreCase(editTicketObject.getTerminal())) {
            rdDomestic.setChecked(true);
        } else {
            rdDomestic.setChecked(false);
            rdInternational.setChecked(false);
        }
        if(editTicketObject.getType().toLowerCase().equalsIgnoreCase("backload")){
            btnSaveAndNext.setVisibility(View.GONE);
            btnVinIncoming.setVisibility(View.VISIBLE);
            btnVinOrTripOutgoing.setVisibility(View.VISIBLE);
            if(editTicketObject.getTripOrVin().toLowerCase().equalsIgnoreCase("trip")){
                btnVinOrTripOutgoing.setText("Edit TRIP for Outgoing");
            }
        }else{
            btnSaveAndNext.setText("Save & Continue To " + editTicketObject.getTripOrVin() + " Edit");
            btnSaveAndNext.setVisibility(View.VISIBLE);
            btnVinIncoming.setVisibility(View.GONE);
            btnVinOrTripOutgoing.setVisibility(View.GONE);
        }

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
        toolbarTitle.setText("Edit Visit");
    }

    @OnClick({R.id.btnSave, R.id.btnCancel, R.id.btnSaveAndNext, R.id.edtDriver, R.id.edtTruck, R.id.btnVinIncoming, R.id.btnVinOrTripOutgoing})
    public void onClick(View view) {
        Intent i = null;
        String type = editTicketObject.getType();
        String vinOrTrip = editTicketObject.getTripOrVin();
        switch (view.getId()) {
            case R.id.btnSave:
                isSaveAndNext = false;
                saveVisitEdit();
                break;
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnSaveAndNext:
                isSaveAndNext = true;
                saveVisitEdit();
                break;
            case R.id.edtDriver:
                i = new Intent(this, SearchDriverActivity.class);
                startActivityForResult(i, 100);
                break;
            case R.id.edtTruck:
                i = new Intent(this, SearchTruckActivity.class);
                startActivityForResult(i, 200);
                break;
            case R.id.btnVinIncoming:
                i = new Intent(this, InputVinActivity.class);
                i.putExtra("title", "Edit VIN");
                i.putExtra("visitId", visitId);
                i.putExtra("jenis", type);
                i.putExtra("isIncoming", true);
                i.putExtra("loading", editTicketObject.getExpectedLoading());
                i.putExtra("unloading", editTicketObject.getExpectedUnloading());
                startActivity(i);
                break;
            case R.id.btnVinOrTripOutgoing:

                if (vinOrTrip.toLowerCase().equalsIgnoreCase("vin")) {
                    i = new Intent(this, InputVinActivity.class);
                    i.putExtra("title", "Edit VIN");
                    i.putExtra("visitId", visitId);
                    i.putExtra("jenis", type);
                    i.putExtra("isIncoming", false);
                    i.putExtra("loading", editTicketObject.getExpectedLoading());
                    i.putExtra("unloading", editTicketObject.getExpectedUnloading());
                    startActivity(i);
                } else {
                    i = new Intent(this, InputTripNoActivity.class);
                    i.putExtra("title", "Edit TRIP");
                    i.putExtra("visitId", visitId);
                    i.putExtra("jenis", type);
                    i.putExtra("isIncoming", false);
                    i.putExtra("loading", editTicketObject.getExpectedLoading());
                    i.putExtra("unloading", editTicketObject.getExpectedUnloading());
                    startActivity(i);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Bundle arguments = data.getExtras();
                DriverObject driver = (DriverObject) arguments.getSerializable("data");
                driverId = driver.getDriverId();
                edtDriver.setText(driver.getDriverName());
            } else if (requestCode == 200) {
                Bundle arguments = data.getExtras();
                TruckObject truck = (TruckObject) arguments.getSerializable("data");
                edtTruck.setText(truck.getPlatNo());
                platNoCode = truck.getPlatNoCode();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveVisitEdit() {
        HttpTask task = new HttpTask(this, Config.URL_EDIT_VISIT_SAVE, 1001, Config.POST, this, SaveEditVisitParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        post.add(new BasicNameValuePair("driver", edtDriver.getText().toString()));
        post.add(new BasicNameValuePair("driver_id", driverId));
        post.add(new BasicNameValuePair("truck_code", platNoCode));
        int selectedId = rdGroup.getCheckedRadioButtonId();
        AppCompatRadioButton radioButton = (AppCompatRadioButton) findViewById(selectedId);
        String check = radioButton.getText().toString();
        post.add(new BasicNameValuePair("visit_direction", check.toUpperCase()));
//        user_id, session_id, visit_id, driver, driver_id, truck_code, visit_direction
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof SaveEditVisitParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                if (isSaveAndNext) {
                    Intent i = null;
                    String type = editTicketObject.getType();
                    String vinOrTrip = editTicketObject.getTripOrVin();
                    if (vinOrTrip.toLowerCase().equalsIgnoreCase("vin")) {
                        i = new Intent(this, InputVinActivity.class);
                        i.putExtra("title", "Edit VIN");
                        i.putExtra("visitId", visitId);
                        i.putExtra("jenis", type);

                        i.putExtra("loading", editTicketObject.getExpectedLoading());
                        i.putExtra("unloading", editTicketObject.getExpectedUnloading());
                        startActivity(i);
                    } else {
                        i = new Intent(this, InputTripNoActivity.class);
                        i.putExtra("title", "Edit TRIP");
                        i.putExtra("visitId", visitId);
                        i.putExtra("jenis", type);
                        i.putExtra("loading", editTicketObject.getExpectedLoading());
                        i.putExtra("unloading", editTicketObject.getExpectedUnloading());
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(this, "Edit success", Toast.LENGTH_SHORT).show();
                    finish();
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
}
