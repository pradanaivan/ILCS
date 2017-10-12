package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ListArrayAdapter;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.ObjectNewSlot;
import com.ikt.main.to.object.ScanVINObject;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.parser.CheckScanVINParser;
import com.ikt.main.to.parser.SimpleParser;
import com.ikt.main.to.parser.SlotParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifins on 10/5/16.
 */

public class IncomingVinActivity extends AppCompatActivity implements View.OnClickListener, IHttpResponse, AdapterView.OnItemSelectedListener, TapView {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtTimeSlot)
    TextView txtTimeSlot;
    @Bind(R.id.timeSlotBtn)
    LinearLayout timeSlotBtn;
    @Bind(R.id.btnAdd)
    Button btnAdd;
    @Bind(R.id.txtIn)
    MaterialEditText txtIn;
    @Bind(R.id.container)
    LinearLayout container;
    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.btnNext)
    Button btnNext;
    @Bind(R.id.btnScan)
    ImageView btnScan;
    @Bind(R.id.spinDate)
    Spinner spinDate;
    @Bind(R.id.spinSlot)
    Spinner spinSlot;
    @Bind(R.id.spinArea)
    Spinner spinArea;
    @Bind(R.id.txtAmountVin)
    MaterialEditText txtAmountVin;
    @Bind(R.id.llScanVin)
    LinearLayout llScanVin;

    private ActionBar mActionBar;
    private int type, proses;
    private String driver, driverId, platNo;
    private StringBuffer vins;
    private TimeSlotObject obj;
    private HashMap<String, String> vinsHash;
    public static String barcodeScan;
    ListArrayAdapter adapterDate;
    ListArrayAdapter adapterSlot;
    ListArrayAdapter adapterArea;
    private List<String> arrDate = new ArrayList<String>();
    private List<String> arrSlot = new ArrayList<String>();
    private List<String> arrArea = new ArrayList<String>();
    private List<String> arrSlotTemp = new ArrayList<String>();
    private String privilige;
    private int previligeInt;
    private String vin_tmp;
    private List<String> vinTmp = new ArrayList<String>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outgoing_vins_input_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
        }
        privilige = PreferenceManagers.getData(Config.KEY_PRIVILEGE, this);
        previligeInt = Integer.parseInt(privilige);
        vins = new StringBuffer();
        txtIn.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        timeSlotBtn.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnScan.setOnClickListener(this);
        adapterDate = new ListArrayAdapter(this, arrDate, arrDate);
        spinDate.setAdapter(adapterDate);
        spinDate.setOnItemSelectedListener(this);
        adapterSlot = new ListArrayAdapter(this, arrSlot, arrSlot);
        spinSlot.setAdapter(adapterSlot);
        adapterArea = new ListArrayAdapter(this, arrArea, arrArea);
        spinArea.setAdapter(adapterArea);
        getSlot();
        if (previligeInt == 0) {
            txtAmountVin.setVisibility(View.VISIBLE);
            llScanVin.setVisibility(View.INVISIBLE);
        } else {
            txtAmountVin.setVisibility(View.INVISIBLE);
            llScanVin.setVisibility(View.VISIBLE);
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
        toolbarTitle.setText(getResources().getString(R.string.incoming));
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd || v == txtIn) {
//            if (!txtIn.getText().toString().isEmpty()) {
//                addVinsNo();
//            }
            Intent i = new Intent(this, SearchVinsActivity.class);
            i.putExtra("jenis", "incoming");
            startActivityForResult(i, 200);
        } else if (v == timeSlotBtn) {
            Intent i = new Intent(this, TimeSlotActivity.class);
            i.putExtra("type", type);
            startActivityForResult(i, 100);
        } else if (v == btnNext) {

            Intent intent = null;
            String area = "";
            String date = "";
            String slot = "";
            ObjectNewSlot objectNewSlot = null;
            vins = new StringBuffer();
            String amountVin = txtAmountVin.getText().toString();
            switch (previligeInt) {
                case 0:
                    if (amountVin.isEmpty()) {
                        Snackbar.make(btnBack, "Please fill Amount of VIN field", Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (proses == 2) {
                            intent = new Intent(this, OutgoingActivity.class);
                        } else {
                            intent = new Intent(this, ConfirmAnnouncementActivity.class);
                        }
                        if (container.getChildCount() > 0) {
                            int count = container.getChildCount();
                            for (int i = 0; i < count; i++) {
                                ViewGroup view = (ViewGroup) container.getChildAt(i);
                                TextView txtVins = (TextView) view.getChildAt(1);
                                if (i == count - 1)
                                    vins.append(txtVins.getText().toString());
                                else
                                    vins.append(txtVins.getText().toString() + "#");
                            }

                            intent.putExtra("vin", vins.toString());
                        }
                        intent.putExtra("vinsAmount", amountVin);

                        area = arrArea.get(spinArea.getSelectedItemPosition());
                        date = arrDate.get(spinDate.getSelectedItemPosition());
                        slot = arrSlot.get(spinSlot.getSelectedItemPosition());
                        objectNewSlot = new ObjectNewSlot();
                        objectNewSlot.setArea_in(area);
                        objectNewSlot.setDate_in(date);
                        objectNewSlot.setSlot_in(slot);
                        VectorModel.getInstance().clearObjectNewSlotIn();
                        VectorModel.getInstance().setObjectNewSlotsIn(objectNewSlot);

                        intent.putExtra("type", type);
                        intent.putExtra("driver", driver);
                        intent.putExtra("driverId", driverId);
                        intent.putExtra("platNo", platNo);
                        intent.putExtra("proses", proses);
                        intent.putExtra("timeSlot", obj);

                        startActivity(intent);
                    }
                    break;
                case 1:
                    if (container.getChildCount() > 0) {
                        if (proses == 2) {
                            intent = new Intent(this, OutgoingActivity.class);
                        } else {
                            intent = new Intent(this, ConfirmAnnouncementActivity.class);
                        }
                        int count = container.getChildCount();
                        for (int i = 0; i < count; i++) {
                            ViewGroup view = (ViewGroup) container.getChildAt(i);
                            TextView txtVins = (TextView) view.getChildAt(1);
                            if (i == count - 1)
                                vins.append(txtVins.getText().toString());
                            else
                                vins.append(txtVins.getText().toString() + "#");
                        }

                        area = arrArea.get(spinArea.getSelectedItemPosition());
                        date = arrDate.get(spinDate.getSelectedItemPosition());
                        slot = arrSlot.get(spinSlot.getSelectedItemPosition());
                        objectNewSlot = new ObjectNewSlot();
                        objectNewSlot.setArea_in(area);
                        objectNewSlot.setDate_in(date);
                        objectNewSlot.setSlot_in(slot);
                        VectorModel.getInstance().clearObjectNewSlotIn();
                        VectorModel.getInstance().setObjectNewSlotsIn(objectNewSlot);

                        intent.putExtra("type", type);
                        intent.putExtra("driver", driver);
                        intent.putExtra("driverId", driverId);
                        intent.putExtra("platNo", platNo);
                        intent.putExtra("proses", proses);
                        intent.putExtra("timeSlot", obj);
                        intent.putExtra("vinsAmount", count + "");
                        intent.putExtra("vin", vins.toString());
                        startActivity(intent);
                    } else {
                        Snackbar.make(btnBack, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Snackbar.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
//                    if (txtAmountVin.getText().toString().isEmpty()) {
//                        Snackbar.make(btnBack, "Please fill Amount of VIN field", Snackbar.LENGTH_SHORT).show();
//                    } else {
                    if (container.getChildCount() > 0) {
                        if (proses == 2) {
                            intent = new Intent(this, OutgoingActivity.class);
                        } else {
                            intent = new Intent(this, ConfirmAnnouncementActivity.class);
                        }

                        int count = container.getChildCount();
                        for (int i = 0; i < count; i++) {
                            ViewGroup view = (ViewGroup) container.getChildAt(i);
                            TextView txtVins = (TextView) view.getChildAt(1);
                            if (i == count - 1)
                                vins.append(txtVins.getText().toString());
                            else
                                vins.append(txtVins.getText().toString() + "#");
                        }

                        intent.putExtra("vin", vins.toString());
                        intent.putExtra("vinsAmount", count);


                        area = arrArea.get(spinArea.getSelectedItemPosition());
                        date = arrDate.get(spinDate.getSelectedItemPosition());
                        slot = arrSlot.get(spinSlot.getSelectedItemPosition());
                        objectNewSlot = new ObjectNewSlot();
                        objectNewSlot.setArea_in(area);
                        objectNewSlot.setDate_in(date);
                        objectNewSlot.setSlot_in(slot);
                        VectorModel.getInstance().clearObjectNewSlotIn();
                        VectorModel.getInstance().setObjectNewSlotsIn(objectNewSlot);

                        intent.putExtra("type", type);
                        intent.putExtra("driver", driver);
                        intent.putExtra("driverId", driverId);
                        intent.putExtra("platNo", platNo);
                        intent.putExtra("proses", proses);
                        intent.putExtra("timeSlot", obj);

                        startActivity(intent);
                    }else {
                        Snackbar.make(btnBack, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Snackbar.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    if (proses == 2) {
                        intent = new Intent(this, OutgoingActivity.class);
                    } else {
                        intent = new Intent(this, ConfirmAnnouncementActivity.class);
                    }
                    if (container.getChildCount() > 0) {
                        int count = container.getChildCount();
                        for (int i = 0; i < count; i++) {
                            ViewGroup view = (ViewGroup) container.getChildAt(i);
                            TextView txtVins = (TextView) view.getChildAt(1);
                            if (i == count - 1)
                                vins.append(txtVins.getText().toString());
                            else
                                vins.append(txtVins.getText().toString() + "#");
                        }


                        area = arrArea.get(spinArea.getSelectedItemPosition());
                        date = arrDate.get(spinDate.getSelectedItemPosition());
                        slot = arrSlot.get(spinSlot.getSelectedItemPosition());
                        objectNewSlot = new ObjectNewSlot();
                        objectNewSlot.setArea_in(area);
                        objectNewSlot.setDate_in(date);
                        objectNewSlot.setSlot_in(slot);
                        VectorModel.getInstance().clearObjectNewSlotIn();
                        VectorModel.getInstance().setObjectNewSlotsIn(objectNewSlot);

                        intent.putExtra("type", type);
                        intent.putExtra("driver", driver);
                        intent.putExtra("driverId", driverId);
                        intent.putExtra("platNo", platNo);
                        intent.putExtra("proses", proses);
                        intent.putExtra("timeSlot", obj);
                        intent.putExtra("vinsAmount", count + "");
                        intent.putExtra("vin", vins.toString());
                        startActivity(intent);
                    } else {
                        Snackbar.make(btnBack, "Please fill all " + getResources().getString(R.string.mandatory) + " field", Snackbar.LENGTH_SHORT).show();
                    }
                    break;
            }

        } else if (v == btnScan) {
            startScan();
        }
    }

    private void getSlot() {
        HttpTask task = new HttpTask(this, Config.URL_GET_SLOT, 1001, Config.POST, this, SlotParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("incoming", "1"));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, PreferenceManagers.getData(Config.KEY_USER_ID, this)));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, PreferenceManagers.getData(Config.KEY_SESSION, this)));
        post.add(new BasicNameValuePair("terminal", type == 0 ? "international" : "domestic"));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
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

    private void addVinsNo(final String text) {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_outgoing_vins_input_layout, null);
        TextView textOut = (TextView) addView.findViewById(R.id.txtOut);
        textOut.setText(text);
        Button buttonRemove = (Button) addView.findViewById(R.id.btnRemove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (vinsHash != null && vinsHash.containsKey(text)) vinsHash.remove(text);
                ((LinearLayout) addView.getParent()).removeView(addView);
            }
        });
        vinTmp.add(text);
        container.addView(addView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            if (requestCode == 100) {
                if (resultCode == RESULT_OK) {
                    Bundle arguments = data.getExtras();
                    obj = (TimeSlotObject) arguments.getSerializable("data");
                    txtTimeSlot.setText(obj.getTimeSlotBookDate() + " - " + obj.getTimeSlotCode() + " - " + obj.getTimeSlotPosition());
                }
            } else if (requestCode == 200) {
                if (resultCode == RESULT_OK) {
                    Bundle arguments = data.getExtras();
                    vinsHash = (HashMap<String, String>) arguments.getSerializable("data");
                    Set<Map.Entry<String, String>> mapSet = vinsHash.entrySet();
                    Iterator<Map.Entry<String, String>> mapIterator = mapSet.iterator();
                    while (mapIterator.hasNext()) {
                        Map.Entry<String, String> mapEntry = mapIterator.next();
                        String keyValue = mapEntry.getKey();
//                    String value = mapEntry.getValue();
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
        post.add(new BasicNameValuePair("incoming", 1 + ""));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(false);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof CheckScanVINParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ScanVINObject scanVinObj = VectorModel.getInstance().getScanVINObjects().get(0);
                if (scanVinObj.isStatus()) {
                    addVinsNo(scanVinObj.getVin());
                } else if (!scanVinObj.isStatus() && scanVinObj.getOffer() == 1) {
                    Utility.createDialogAnnounceVin(IncomingVinActivity.this, "Announce VIN", scanVinObj.getMessage() + "\n" + getResources().getString(R.string.announce_vin), scanVinObj.getVin(), this);
                } else {
                    Toast.makeText(context, scanVinObj.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (result instanceof SlotParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                try {
                    ArrayList<String> arrDateTmp = VectorModel.getInstance().getObjectNewSlotsDateIn();
                    for (int i = 0; i < arrDateTmp.size(); i++) {
                        arrDate.add(arrDateTmp.get(i));
                    }
                    arrSlotTemp = VectorModel.getInstance().getObjectNewSlotsSlotIn();

                    String tmpArr = arrSlotTemp.get(0);
                    JSONArray array = new JSONArray(tmpArr);
                    for (int i = 0; i < array.length(); i++) {
                        arrSlot.add(array.optString(i));
                    }
                    ArrayList<String> arrAreaTmp = VectorModel.getInstance().getObjectNewSlotsAreaIn();
                    for (int i = 0; i < arrAreaTmp.size(); i++) {
                        arrArea.add(arrAreaTmp.get(i));
                    }
                    adapterArea.notifyDataSetChanged();
                    adapterSlot.notifyDataSetChanged();
                    adapterDate.notifyDataSetChanged();

//                    spinDate.setAdapter(adapterDate);
//                    spinArea.setAdapter(adapterArea);
//                    spinSlot.setAdapter(adapterSlot);
                } catch (JSONException e) {

                }
            }
        } else if (result instanceof SimpleParser) {
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        try {
            int index = spinDate.getSelectedItemPosition();
            if (arrSlotTemp.size() > 0) {
                String tmpArr = arrSlotTemp.get(index);
                JSONArray array = new JSONArray(tmpArr);
                arrSlot.clear();
                for (int i = 0; i < array.length(); i++) {
                    arrSlot.add(array.optString(i));
                }

                adapterSlot.notifyDataSetChanged();
                spinSlot.setAdapter(adapterSlot);
            }
        } catch (JSONException e) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setAction(int type, int position, String vin) {
        if (type == 99) {
            vin_tmp = vin;
            announceVin(vin);
//            addVinsNo(vin);
        }
    }

    private void announceVin(String vin) {
        HttpTask task = new HttpTask(this, Config.URL_ANNOUNCE_VIN, 1003, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("incoming", "1"));
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
