package com.ikt.main.to.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.TruckActivitiesDetailAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TruckActivitiesDetailObject;
import com.ikt.main.to.parser.TruckActivitiesDetailParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TruckActivitiesDetailActivity extends BaseActivity2 implements TapView,IHttpResponse {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    @Bind(R.id.text_plat_no)
    TextView text_plat_no;

    @Bind(R.id.text_description)
    TextView text_description;

    @Bind(R.id.text_driver)
    TextView text_driver;

    @Bind(R.id.listTruckActivities)
    RecyclerView listTruckActivities;

    private ActionBar mActionBar;
    private String visitId;
    private String orgId,session,userId;
    private int smallerDimension;
    private boolean isAssign;
    private String mTruckNo = "";
    private String mDesc = "";
    ArrayList<TruckActivitiesDetailObject> data;
    private String mDriver;
    private TruckActivitiesDetailAdapter mAdapter;
    private int pos = -1;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_truck_activities_layout);
        ButterKnife.bind(this);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        Bundle bundle = getIntent().getExtras();

        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        if (bundle != null) {
            pos = bundle.getInt("position", -1);
            visitId = bundle.getString("visitId");
            isAssign = bundle.getBoolean("isAssign");
            mTruckNo = bundle.getString("truck");
            mDriver = bundle.getString("driver");
            text_driver.setText(mDriver);
            text_plat_no.setText(mTruckNo);
            text_description.setText(mDesc);
        }

        listTruckActivities.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listTruckActivities.setLayoutManager(layoutManager);

        data = new ArrayList<TruckActivitiesDetailObject>();
        mAdapter = new TruckActivitiesDetailAdapter(this, data);
        mAdapter.setListener(this);
        listTruckActivities.setAdapter(mAdapter);

        setToolBar();
        getTruckActivitiesDetail();
    }

    private void getTruckActivitiesDetail(){
        HttpTask task = new HttpTask(this, Config.URL_GET_ACTIVITIES, 1001, Config.POST, this, TruckActivitiesDetailParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair(Config.KEY_VISIT_ID, visitId));
        post.add(new BasicNameValuePair(Config.TRUCK, mTruckNo));
//        //hardcode
//        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, "3959341771344691265"));
//        post.add(new BasicNameValuePair(Config.KEY_SESSION, "1"));
//        post.add(new BasicNameValuePair(Config.KEY_USER_ID, "1"));
//        post.add(new BasicNameValuePair(Config.TRUCK, "B9003WV"));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
//            mActionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
//            mActionBar.setIcon(R.drawable.logo);
//            mActionBar.setTitle(R.string.label_detail_truck_status);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.abc_grow_fade_in_from_bottom,
                        R.anim.abc_shrink_fade_out_from_bottom)
                .replace(R.id.content_frame, fragment)
                .commit();
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
    public void setAction(int type, int position, String name) {
        if (type==1) {
            // show popup
            TruckActivitiesDetailObject obj = (TruckActivitiesDetailObject) data.get(position);
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.popup_notif_layout);
            dialog.setTitle(obj.getVisitId());

            TextView text_driver = (TextView) dialog.findViewById(R.id.text_driver);
            TextView text_type = (TextView) dialog.findViewById(R.id.text_type);
            text_type.setVisibility(View.GONE);
            TextView text_outgoing_voy_nr = (TextView) dialog.findViewById(R.id.text_outgoing_voy_nr);
            TextView text_outgoing_vessel = (TextView) dialog.findViewById(R.id.text_outgoing_vessel);
            TextView text_time_begin = (TextView) dialog.findViewById(R.id.text_time_begin);
            TextView text_time_end = (TextView) dialog.findViewById(R.id.text_time_end);

            TextView text_load_lanes_in = (TextView) dialog.findViewById(R.id.text_load_lanes_in);
            TextView text_load_lanes_out = (TextView) dialog.findViewById(R.id.text_load_lanes_out);

            text_driver.setText(obj.getDriver());
            text_type.setText(obj.getLast_status());
            text_outgoing_voy_nr.setText(obj.getOutgoing_voyage_nr().equalsIgnoreCase("null") ? "-" : obj.getOutgoing_voyage_nr());
            text_outgoing_vessel.setText(obj.getOutgoing_vessel().equalsIgnoreCase("null") ? "-" : obj.getOutgoing_vessel());
            text_time_begin.setText(obj.getTime_begin());
            text_time_end.setText(obj.getTime_end());
            text_load_lanes_in.setText(obj.getLoadarea_in());
            text_load_lanes_out.setText(obj.getLoadarea_out());

            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(this);
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof TruckActivitiesDetailParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                ArrayList<TruckActivitiesDetailObject> ticketDetailsArr = VectorModel.getInstance().getTruckActivitiesDetailObjects();
                if(ticketDetailsArr != null){
                    data.clear();
                    data.addAll(ticketDetailsArr);
                }
                mAdapter.notifyDataSetChanged();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            if(pos == -1){
                Intent i = new Intent(this, HomeActivity.class);
                i.putExtra("pos", 2);
                startActivity(i);
                finish();
            }else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(pos == -1){
            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("pos", 2);
            startActivity(i);
            finish();
        }else {
            super.onBackPressed();
        }
    }
}