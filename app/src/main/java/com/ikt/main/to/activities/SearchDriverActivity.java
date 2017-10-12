package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.DriverAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.parser.DriverParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/8/16.
 */
public class SearchDriverActivity extends BaseActivity2 implements TapView, SearchView.OnQueryTextListener, IHttpResponse, View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.container)
    LinearLayout container;
    @Bind(R.id.btnAddDriver)
    Button btnAddDriver;

    private ActionBar mActionBar;
    private MenuItem searchMenuItem;
    private SearchView mSearchView;
    private DriverAdapter adapter;
    private ArrayList<DriverObject> drivers;
    private DBHelper dbHelper;
    private String orgId;
    private String query;
    private List<DriverObject> arrDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        ButterKnife.bind(this);
        setToolBar();
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        dbHelper = new DBHelper(this);

        drivers = new ArrayList<DriverObject>();
        adapter = new DriverAdapter(this, drivers, this);
        listView.setAdapter(adapter);
        btnAddDriver.setOnClickListener(this);
        int count = dbHelper.getDriverCount();
        if (count > 0) {
            arrDriver = dbHelper.getAllDriver();
            for (int i = 0; i < arrDriver.size(); i++) {
                DriverObject driver = arrDriver.get(i);
                drivers.add(driver);
            }
            adapter.notifyDataSetChanged();
        } else {
            getDrivers();
        }
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
        final int textViewID = mSearchView.getResources().getIdentifier("android:id/search_src_text", null, null);
        final AutoCompleteTextView searchTextView = (AutoCompleteTextView) mSearchView.findViewById(textViewID);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.color_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
        }
        changeSearchViewTextColor(mSearchView);
        return true;
    }

    @Override
    public boolean isAvailableSearchBtn() {
        return true;
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

    //    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        DriverObject driver = drivers.get(position);
//        Intent data = new Intent();
//        data.putExtra("data", driver);
//        setResult(RESULT_OK, data);
//        finish();
//    }

    @Override
    public boolean onQueryTextSubmit(String query) {
//        int count = dbHelper.getDriverCount();
//        if(count > 0) {
//            List<DriverObject> arrDrivers = dbHelper.getDriverByName(query);
//            for (int i = 0; i < arrDrivers.size(); i++) {
//                DriverObject driver = arrDrivers.get(i);
//                drivers.add(driver);
//            }
//            adapter.notifyDataSetChanged();
//        }else {
//            this.query = query;
//            getDrivers();
//        }
        return false;
    }

    private void getDrivers() {
        HttpTask task = new HttpTask(this, Config.URL_DRIVER, 1001, Config.POST, this, DriverParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        DriverObject object = dbHelper.getLastDriver();
        if (object != null)
            post.add(new BasicNameValuePair(Config.KEY_LAST_ID, object.getDriverId() + ""));
        else
            post.add(new BasicNameValuePair(Config.KEY_LAST_ID, "0"));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()) {
            if (arrDriver != null && arrDriver.size() > 0) {
                drivers.clear();
                for (int i = 0; i < arrDriver.size(); i++) {
                    String driverName = arrDriver.get(i).getDriverName();
                    if (driverName.toLowerCase().contains(newText.toLowerCase())) {
                        DriverObject driver = arrDriver.get(i);
                        drivers.add(driver);
                    }
                }
            }else{
                arrDriver = dbHelper.getAllDriver();
                for (int i = 0; i < arrDriver.size(); i++) {
                    String driverName = arrDriver.get(i).getDriverName();
                    if (driverName.toLowerCase().contains(newText.toLowerCase())) {
                        DriverObject driver = arrDriver.get(i);
                        drivers.add(driver);
                    }
                }
            }
        } else {
            int count = dbHelper.getDriverCount();
            if (count > 0) {
                drivers.clear();
                arrDriver = dbHelper.getAllDriver();
                for (int i = 0; i < arrDriver.size(); i++) {
                    DriverObject driver = arrDriver.get(i);
                    drivers.add(driver);
                }
            }
        }
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void setAction(int type, int position, String name) {
        DriverObject driver = drivers.get(position);
        Intent data = new Intent();
        data.putExtra("data", driver);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(this);
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof DriverParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                drivers.clear();
                arrDriver = dbHelper.getAllDriver();
                for (int i = 0; i < arrDriver.size(); i++) {
                    DriverObject driver = arrDriver.get(i);
                    drivers.add(driver);
                }
                adapter.notifyDataSetChanged();
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
    public void onClick(View v) {
        if(v == btnAddDriver){
            Intent i = new Intent(this, AddDriverActivity.class);
            startActivity(i);
        }
    }
}
