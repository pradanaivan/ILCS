package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.TimeSlotAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.parser.TimeSlotParser;
import com.ikt.main.to.util.Config;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/15/16.
 */
public class TimeSlotActivity extends BaseActivity2 implements SearchView.OnQueryTextListener,TapView,IHttpResponse{

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.headerLayout)
    LinearLayout headerLayout;
    @Bind(R.id.listView)
    RecyclerView listView;
    @Bind(R.id.container)
    LinearLayout container;

    private ActionBar mActionBar;
    private MenuItem searchMenuItem;
    private SearchView mSearchView;
    private ArrayList<TimeSlotObject> data;
    private TimeSlotAdapter adapter;
    private String[] drivers = {"DOMIR", "ZAHIR", "JENKINS", "SONNY", "ZAKII", "DONNY", "DANNY", "AGUS", "LUKMAN", "SABDA"};
    private int type;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_slot_layout);
        ButterKnife.bind(this);
        setToolBar();
        data = new ArrayList<TimeSlotObject>();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            type = bundle.getInt("type");
            if(type == 0){
                id = bundle.getString("nr");
            }
        }
        getTimeSLot();
//        setData();
    }

    private void getTimeSLot(){
        String url = "";
        switch (type){
            case 0:
                url = Config.URL_TIMESLOT_INTERNATIONAL + "?id="+id;
                break;
            case 1:
                url = Config.URL_TIMESLOT_DOMESTIC;
                break;
        }

        HttpTask task = new HttpTask(this, url, 1001, Config.GET, this, TimeSlotParser.class);
        task.setProcessName(getString(R.string.loading_load));
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

//    private void setData(){
//        data = new ArrayList<TimeSlotObject>();
//        for(int i=0;i < 10;i++){
//            Random rn = new Random();
//            int available = i + rn.nextInt(10 - i + 1);
//            TimeSlotObject slotObject = new TimeSlotObject();
//            slotObject.setAvailable(available+"");
//            slotObject.setTimeSlot("11.09.2014-D1_EVENING_RECEIVING - "+drivers[i]);
//            data.add(slotObject);
//        }
//
//        adapter = new TimeSlotAdapter(this,data);
//        adapter.setListener(this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        listView.setLayoutManager(layoutManager);
//        listView.setAdapter(adapter);
//    }

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
        toolbarTitle.setText(getResources().getString(R.string.action_search));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.expandActionView();
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.onActionViewExpanded();
//        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        final int textViewID = mSearchView.getResources().getIdentifier("android:id/search_src_text",null, null);
        final AutoCompleteTextView searchTextView = (AutoCompleteTextView) mSearchView.findViewById(textViewID);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.color_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {}
        changeSearchViewTextColor(mSearchView);
        return true;
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
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
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<TimeSlotObject> tmp = new ArrayList<TimeSlotObject>();
        if(!newText.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                TimeSlotObject slotObject = data.get(i);
                if (slotObject.getTimeSlotCode().toLowerCase().contains(newText.toLowerCase())) {
                    tmp.add(slotObject);
                }
            }
        }else{
            for (int i = 0; i < data.size(); i++) {
                TimeSlotObject slotObject = data.get(i);
                tmp.add(slotObject);
            }
        }
        data.clear();
        data.addAll(tmp);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void setAction(int type, int position, String name) {
        TimeSlotObject object = data.get(position);
        Intent data = new Intent();
        data.putExtra("data", object);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof TimeSlotParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                ArrayList<TimeSlotObject> timeSlotObjects = VectorModel.getInstance().getTimeSlotObjectVector();
                data.addAll(timeSlotObjects);
                adapter = new TimeSlotAdapter(this,data);
                adapter.setListener(this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(this,parser.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {

    }
}
