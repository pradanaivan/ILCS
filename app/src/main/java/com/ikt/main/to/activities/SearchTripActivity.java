package com.ikt.main.to.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.adapter.SearchTripAdapter;
import com.ikt.main.to.adapter.SearchVinsAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.parser.TripsParser;
import com.ikt.main.to.parser.VinParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifins on 10/10/16.
 */

public class SearchTripActivity extends BaseActivity2 implements IHttpResponse, SearchView.OnQueryTextListener, TapView{

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.container)
    LinearLayout container;

    private ActionBar mActionBar;
    private MenuItem searchMenuItem;
    private SearchView mSearchView;
    private String orgId, session, userId, jenis;
    private String min = "0";
    private String max = "100";
    private ArrayList<String> searchResult = new ArrayList<String>();
    private SearchTripAdapter adapter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_general_layout);
        ButterKnife.bind(this);
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            jenis = bundle.getString("jenis");
        }
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, this);
        session = PreferenceManagers.getData(Config.KEY_SESSION, this);
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, this);
        adapter = new SearchTripAdapter(this, searchResult, this);
        listView.setAdapter(adapter);
        listView.setVisibility(View.GONE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.expandActionView();
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.onActionViewExpanded();
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(mSearchView.getQuery().length() > 3){
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getTrips();
        }
        return false;
    }

    private void getTrips() {
        HttpTask task = new HttpTask(this, Config.URL_GET_TRIPS, 1001, Config.POST, this, TripsParser.class);
        task.setProcessName(getString(R.string.loading_load));
        task.setEnabledProgressDialog(false);
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        if(jenis.equalsIgnoreCase("outgoing")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        } else if(jenis.equalsIgnoreCase("backload")) {
            post.add(new BasicNameValuePair("incoming", "0"));
        }
        else{
            post.add(new BasicNameValuePair("incoming", "1"));
        }
        post.add(new BasicNameValuePair("min", min));
        post.add(new BasicNameValuePair("max", max));
        post.add(new BasicNameValuePair("key", mSearchView.getQuery().toString().toUpperCase()));
        task.setPostData(post);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof TripsParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                searchResult.clear();
                searchResult.addAll(VectorModel.getInstance().getArrTrip());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        progressBar.setVisibility(View.GONE);
        IParser parser = (IParser) result;
        Toast.makeText(this, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {

    }

    @Override
    public void setAction(int type, int position, String name) {
        Intent data = new Intent();
        data.putExtra("data", name);
        setResult(RESULT_OK, data);
        finish();
    }
}
