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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.EditTicketObject;
import com.ikt.main.to.object.ScanVINObject;
import com.ikt.main.to.parser.AnnounceVinParser;
import com.ikt.main.to.parser.CheckScanVINParser;
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
 * Created by Arifin on 9/28/16.
 */

public class InputVinActivity extends AppCompatActivity implements IHttpResponse, View.OnClickListener, TapView {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtIn)
    MaterialEditText txtIn;
    @Bind(R.id.btnAdd)
    Button btnAdd;
    @Bind(R.id.container)
    LinearLayout container;
    @Bind(R.id.btnSaveOrCheck)
    Button btnSaveOrCheck;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.btnScan)
    ImageView btnScan;
    @Bind(R.id.txtAmountVinLoading)
    MaterialEditText txtAmountVinLoading;
    @Bind(R.id.txtAmountVinUnloading)
    MaterialEditText txtAmountVinUnloading;
    @Bind(R.id.llScanVin)
    LinearLayout llScanVin;

    private ActionBar mActionBar;
    private int type, proses;
    private String driver, driverId, platNo, title;
    private String orgId, session, userId, visitId, jenis;
    private EditTicketObject editTicketObject;
    private HashMap<String, String> arrAction = new HashMap<String, String>();
    private HashMap<String, String> vinsHash;
    private boolean isIncoming;
    private List<String> vinTmp = new ArrayList<String>();
    private String vin_tmp;
    private String amountLoading;
    private String amountUnloading;
    private String privilege;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_vin_layout);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses", -1);
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            title = bundle.getString("title");
            visitId = bundle.getString("visitId", "");
            jenis = bundle.getString("jenis", "");
            isIncoming = bundle.getBoolean("isIncoming");
            amountLoading = bundle.getString("loading", "0");
            amountUnloading = bundle.getString("unloading", "0");
        }
        setToolBar();
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        privilege = PreferenceManagers.getData(Config.KEY_PRIVILEGE, this);
        int privilegeInt = Integer.parseInt(privilege);
        txtIn.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSaveOrCheck.setOnClickListener(this);

        if(privilegeInt == 0){
            llScanVin.setVisibility(View.GONE);
            txtAmountVinLoading.setVisibility(View.VISIBLE);
            txtAmountVinUnloading.setVisibility(View.VISIBLE);
        }else{
            llScanVin.setVisibility(View.VISIBLE);
            txtAmountVinLoading.setVisibility(View.GONE);
            txtAmountVinUnloading.setVisibility(View.GONE);
        }
        if (proses == 0 || jenis.equalsIgnoreCase("incoming")) {
            txtAmountVinUnloading.setText(amountUnloading);
            txtAmountVinLoading.setVisibility(View.GONE);
        } else if (proses == 1 || jenis.equalsIgnoreCase("outgoing")) {
            txtAmountVinLoading.setText(amountLoading);
            txtAmountVinUnloading.setVisibility(View.GONE);
        } else {
            if(isIncoming) {
                txtAmountVinUnloading.setText(amountUnloading);
                txtAmountVinLoading.setVisibility(View.GONE);
            }else {
                txtAmountVinLoading.setText(amountLoading);
                txtAmountVinUnloading.setVisibility(View.GONE);
            }

        }
        if (visitId == null) {
            btnSaveOrCheck.setText("Cancel");
            btnCancel.setText("Next");
        }
        if (visitId != null && visitId.length() > 0) {
            txtIn.setEnabled(true);
            getVin();
        }
    }

    private void getVin() {
        HttpTask task = new HttpTask(this, Config.URL_EDIT_VIN_OUTGOING, 1001, Config.POST, this, VinParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        if (jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        } else if (jenis.toLowerCase().equalsIgnoreCase("backload")) {
            if (!isIncoming) {
                post.add(new BasicNameValuePair("incoming", "0"));
            } else {
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        } else {
            post.add(new BasicNameValuePair("incoming", "1"));
        }

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void checkVin() {
        HttpTask task = new HttpTask(this, Config.URL_CHECK_VIN, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair("vin", txtIn.getText().toString().toUpperCase()));
        post.add(new BasicNameValuePair("incoming", "1"));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd || v == txtIn) {
//            if (!txtIn.getText().toString().isEmpty()) {
            if (visitId == null) {
                checkVin();
            } else {
                Intent i = new Intent(this, SearchVinsActivity.class);
                i.putExtra("jenis", jenis);
                i.putExtra("isIncoming", isIncoming);
                startActivityForResult(i, 200);
//                    addVinsNo(txtIn.getText().toString());
            }
//            }
        } else if (v == btnCancel) {
            if (visitId == null) {
                Intent data = new Intent();
                data.putExtra("data", arrAction);
                setResult(RESULT_OK, data);
                finish();
            } else {
                finish();
            }
        } else if (v == btnSaveOrCheck) {
            if (visitId == null) {
                finish();
            } else {
//                if (container.getChildCount() > 0) {
                saveVin();
//                } else {
//                    Snackbar.make(btnSaveOrCheck, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Snackbar.LENGTH_SHORT).show();
//                }
            }
        } else if (v == btnScan) {
            startScan();
        }
    }

    private void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan Barcode");
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    private void saveVin() {
        String previlige = PreferenceManagers.getData(Config.KEY_PRIVILEGE, this);
        int previligeInt = Integer.parseInt(previlige);
        HttpTask task = new HttpTask(this, Config.URL_EDIT_SAVE_VIN, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        String inc = "0";
        if (jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        } else if (jenis.toLowerCase().equalsIgnoreCase("backload")) {
            if (!isIncoming) {
                post.add(new BasicNameValuePair("incoming", "0"));
            } else {
                inc = "1";
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        } else {
            inc = "1";
            post.add(new BasicNameValuePair("incoming", "1"));
        }
        StringBuilder arrVin = new StringBuilder();
        StringBuilder arrVinAct = new StringBuilder();
        switch (previligeInt) {
            case 0:
                if (arrAction.size() > 0) {
                    Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                    Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                    while (mapIterator.hasNext()) {
                        Map.Entry<String, String> mapEntry = mapIterator.next();
                        String keyValue = mapEntry.getKey();
                        String value = mapEntry.getValue();
                        if (!"0".equalsIgnoreCase(value)) {
                            arrVin.append(keyValue).append("#");
                            arrVinAct.append(value).append("#");
                        }
                    }
                    post.add(new BasicNameValuePair("vin", arrVin.toString()));
                    post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                }
                post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                task.setPostData(post);
                task.setEnabledProgressDialog(true);
                task.setCancelableProgressDialog(true);
                HttpManager.getInstance().doRequest(task);
                break;
            case 1:
                if (inc.equalsIgnoreCase("1")) {
                    if (arrAction.size() > 0) {
                        Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                        Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                        while (mapIterator.hasNext()) {
                            Map.Entry<String, String> mapEntry = mapIterator.next();
                            String keyValue = mapEntry.getKey();
                            String value = mapEntry.getValue();
                            if (!"0".equalsIgnoreCase(value)) {
                                arrVin.append(keyValue).append("#");
                                arrVinAct.append(value).append("#");
                            }
                        }
                        post.add(new BasicNameValuePair("vin", arrVin.toString()));
                        post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                        post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                        post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                        task.setPostData(post);
                        task.setEnabledProgressDialog(true);
                        task.setCancelableProgressDialog(true);
                        HttpManager.getInstance().doRequest(task);
                    } else {
                        Toast.makeText(InputVinActivity.this, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (arrAction.size() > 0) {
                        Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                        Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                        while (mapIterator.hasNext()) {
                            Map.Entry<String, String> mapEntry = mapIterator.next();
                            String keyValue = mapEntry.getKey();
                            String value = mapEntry.getValue();
                            if (!"0".equalsIgnoreCase(value)) {
                                arrVin.append(keyValue).append("#");
                                arrVinAct.append(value).append("#");
                            }
                        }
                        post.add(new BasicNameValuePair("vin", arrVin.toString()));
                        post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                    }
                    post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                    post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                    task.setPostData(post);
                    task.setEnabledProgressDialog(true);
                    task.setCancelableProgressDialog(true);
                    HttpManager.getInstance().doRequest(task);
                }
                break;
            case 2:
                if (inc.equalsIgnoreCase("0")) {
                    if (arrAction.size() > 0) {
                        Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                        Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                        while (mapIterator.hasNext()) {
                            Map.Entry<String, String> mapEntry = mapIterator.next();
                            String keyValue = mapEntry.getKey();
                            String value = mapEntry.getValue();
                            if (!"0".equalsIgnoreCase(value)) {
                                arrVin.append(keyValue).append("#");
                                arrVinAct.append(value).append("#");
                            }
                        }
                        post.add(new BasicNameValuePair("vin", arrVin.toString()));
                        post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                        post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                        post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                        task.setPostData(post);
                        task.setEnabledProgressDialog(true);
                        task.setCancelableProgressDialog(true);
                        HttpManager.getInstance().doRequest(task);
                    } else {
                        Toast.makeText(InputVinActivity.this, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (arrAction.size() > 0) {
                        Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                        Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                        while (mapIterator.hasNext()) {
                            Map.Entry<String, String> mapEntry = mapIterator.next();
                            String keyValue = mapEntry.getKey();
                            String value = mapEntry.getValue();
                            if (!"0".equalsIgnoreCase(value)) {
                                arrVin.append(keyValue).append("#");
                                arrVinAct.append(value).append("#");
                            }
                        }
                        post.add(new BasicNameValuePair("vin", arrVin.toString()));
                        post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                    }
                    post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                    post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                    task.setPostData(post);
                    task.setEnabledProgressDialog(true);
                    task.setCancelableProgressDialog(true);
                    HttpManager.getInstance().doRequest(task);
                }
                break;
            case 3:
                if (arrAction.size() > 0) {
                    Set<Map.Entry<String, String>> mapSet = arrAction.entrySet();
                    Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                    while (mapIterator.hasNext()) {
                        Map.Entry<String, String> mapEntry = mapIterator.next();
                        String keyValue = mapEntry.getKey();
                        String value = mapEntry.getValue();
                        if (!"0".equalsIgnoreCase(value)) {
                            arrVin.append(keyValue).append("#");
                            arrVinAct.append(value).append("#");
                        }
                    }
                    post.add(new BasicNameValuePair("vin", arrVin.toString()));
                    post.add(new BasicNameValuePair("action", arrVinAct.toString()));
                    post.add(new BasicNameValuePair("loading", txtAmountVinLoading.getText().toString()));
                    post.add(new BasicNameValuePair("unloading", txtAmountVinUnloading.getText().toString()));
                    task.setPostData(post);
                    task.setEnabledProgressDialog(true);
                    task.setCancelableProgressDialog(true);
                    HttpManager.getInstance().doRequest(task);
                } else {
                    Toast.makeText(InputVinActivity.this, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void addVinsNo(final String value) {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_outgoing_vins_input_layout, null);
        TextView textOut = (TextView) addView.findViewById(R.id.txtOut);
        textOut.setText(value);
        Button buttonRemove = (Button) addView.findViewById(R.id.btnRemove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (visitId == null) {
                    arrAction.remove(value);
                } else {
                    arrAction.put(value, "-1");
                }
                ((LinearLayout) addView.getParent()).removeView(addView);
            }
        });
        if (arrAction.containsKey(value)) {
            arrAction.put(value, "0");
        } else {
            arrAction.put(value, "1");
        }
        vinTmp.add(value);
        container.addView(addView);
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof VinParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ArrayList<String> vins = VectorModel.getInstance().getArrVin();
                for (int i = 0; i < vins.size(); i++) {
                    addVinsNo(vins.get(i));
                }
            }
        } else if (result instanceof SimpleParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                if (visitId == null) {
                    addVinsNo(txtIn.getText().toString().toUpperCase());
                } else {
                    Toast.makeText(this, "Successfully Save", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("pos", 1);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        } else if (result instanceof CheckScanVINParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ScanVINObject scanVinObj = VectorModel.getInstance().getScanVINObjects().get(0);
                if (scanVinObj.isStatus() && scanVinObj.getOffer() != 1) {
                    addVinsNo(scanVinObj.getVin());
                } else if (!scanVinObj.isStatus() && scanVinObj.getOffer() == 1) {
                    Utility.createDialogAnnounceVin(InputVinActivity.this, "Announce VIN", scanVinObj.getMessage() + "\n" + getResources().getString(R.string.announce_vin), scanVinObj.getVin(), this);
                } else {
                    Toast.makeText(context, scanVinObj.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (result instanceof AnnounceVinParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                addVinsNo(vin_tmp);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("IncomingVinActivity", "Cancelled scan");
            } else {
                Log.d("IncomingVinActivity", "Scanned");
                String valueRawBarcode = result.getContents();
                if(vinTmp.contains(valueRawBarcode)){
                    Toast.makeText(this, getResources().getString(R.string.vin_can_not_twice), Toast.LENGTH_SHORT).show();
                }else{
                    checkVINScan(valueRawBarcode);
                }
            }
        } else {
            if (requestCode == 200) {
                if (resultCode == RESULT_OK) {
                    Bundle arguments = data.getExtras();
                    vinsHash = (HashMap<String, String>) arguments.getSerializable("data");
                    Set<Map.Entry<String, String>> mapSet = vinsHash.entrySet();
                    Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                    while (mapIterator.hasNext()) {
                        Map.Entry<String, String> mapEntry = mapIterator.next();
                        String keyValue = mapEntry.getKey();
                        addVinsNo(keyValue);
                    }
                }
            }
        }
    }

    private void checkVINScan(String val) {
//        Toast.makeText(this, "vin="+val+"&incoming="+1, Toast.LENGTH_SHORT).show();
        HttpTask task = new HttpTask(this, Config.URL_CHECK_VIN_SCAN, 1004, Config.POST, this, CheckScanVINParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("vin", val));
        if (jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        } else if (jenis.toLowerCase().equalsIgnoreCase("backload")) {
            if (!isIncoming) {
                post.add(new BasicNameValuePair("incoming", "0"));
            } else {
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        } else {
            post.add(new BasicNameValuePair("incoming", "1"));
        }
        task.setPostData(post);
        task.setEnabledProgressDialog(false);
        task.setCancelableProgressDialog(false);
        HttpManager.getInstance().doRequest(task);
    }

    public void setAction(int type, int position, String vin) {
        if (type == 99) {
            vin_tmp = vin;
            announceVin(vin);
//            addVinsNo(vin);
        }
    }

    private void announceVin(String vin) {
        HttpTask task = new HttpTask(this, Config.URL_ANNOUNCE_VIN, 1003, Config.POST, this, AnnounceVinParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        if (jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        } else if (jenis.toLowerCase().equalsIgnoreCase("backload")) {
            if (!isIncoming) {
                post.add(new BasicNameValuePair("incoming", "0"));
            } else {
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        } else {
            post.add(new BasicNameValuePair("incoming", "1"));
        }
        post.add(new BasicNameValuePair("vin", vin));
        post.add(new BasicNameValuePair("visitDirection", type == 0 ? "international" : "domestic"));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, PreferenceManagers.getData(Config.KEY_USER_ID, this)));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, PreferenceManagers.getData(Config.KEY_SESSION, this)));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }
}
