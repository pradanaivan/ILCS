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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TripObject;
import com.ikt.main.to.parser.SimpleParser;
import com.ikt.main.to.parser.TripNoParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 9/28/16.
 */

public class InputTripNoActivity extends AppCompatActivity implements View.OnClickListener, IHttpResponse{

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtNoOfTrip)
    MaterialEditText edtNoOfTrip;
    @Bind(R.id.edtTruckCapacity)
    MaterialEditText edtTruckCapacity;
    @Bind(R.id.btnSaveOrCheck)
    Button btnSaveOrCheck;
    @Bind(R.id.btnCancel)
    Button btnCancel;

    private ActionBar mActionBar;
    private int type, proses;
    private String driver, driverId, platNo, title, visitId, jenis;
    private String orgId, session, userId;
    private String oldTrip = "";
    private boolean isIncoming;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_trip_no_layout);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type");
            proses = bundle.getInt("proses");
            driver = bundle.getString("driver");
            driverId = bundle.getString("driverId");
            platNo = bundle.getString("platNo");
            title = bundle.getString("title");
            visitId = bundle.getString("visitId", "");
            jenis = bundle.getString("jenis", "");
            isIncoming = bundle.getBoolean("isIncoming");
        }
        setToolBar();
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        edtNoOfTrip.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSaveOrCheck.setOnClickListener(this);
        if(visitId.length() > 0 && jenis.toLowerCase().equalsIgnoreCase("outgoing") || jenis.toLowerCase().equalsIgnoreCase("backload")){
            getTrip();
        }
    }

    private void getTrip(){
        HttpTask task = new HttpTask(this, Config.URL_EDIT_TRIP_OUTGOING, 1001, Config.POST, this, TripNoParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        if(jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        }else if(jenis.toLowerCase().equalsIgnoreCase("backload")){
            if( !isIncoming){
                post.add(new BasicNameValuePair("incoming", "0"));
            }else{
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        }else{
            post.add(new BasicNameValuePair("incoming", "1"));
        }

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void saveTrip(){
        HttpTask task = new HttpTask(this, Config.URL_EDIT_SAVE_TRIP, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        post.add(new BasicNameValuePair("old_trip", oldTrip));
        post.add(new BasicNameValuePair("new_trip", edtNoOfTrip.getText().toString()));
        post.add(new BasicNameValuePair("capacity", edtTruckCapacity.getText().toString()));
        if(jenis.toLowerCase().equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        }else if(jenis.toLowerCase().equalsIgnoreCase("backload")){
            if( !isIncoming){
                post.add(new BasicNameValuePair("incoming", "0"));
            }else{
                post.add(new BasicNameValuePair("incoming", "1"));
            }
        }else{
            post.add(new BasicNameValuePair("incoming", "1"));
        }

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
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == btnCancel){
            finish();
        }else if(v == btnSaveOrCheck){
            saveTrip();
        }else if(v == edtNoOfTrip){
            Intent i = new Intent(this, SearchTripActivity.class);
            i.putExtra("jenis", jenis);
            startActivityForResult(i, 200);
        }
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof TripNoParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                ArrayList<TripObject> tripObjects = VectorModel.getInstance().getTripObjects();
                TripObject tripObject = tripObjects.get(0);
                oldTrip = tripObject.getTrip();
                edtNoOfTrip.setText(tripObject.getTrip());
                edtTruckCapacity.setText(tripObject.getCapacity());
            }
        }else if(result instanceof SimpleParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                Toast.makeText(this, "Successfully save", Toast.LENGTH_SHORT).show();
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
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 200){
                Bundle arguments = data.getExtras();
                String trip = (String) arguments.getSerializable("data");
                edtNoOfTrip.setText(trip);
            }
        }
    }
}
