package com.ikt.main.to.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ikt.main.to.model.TicketObjectModel;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.TruckActivitiesDetailActivity;
import com.ikt.main.to.adapter.TruckActivitiesAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TruckActivitiesObject;
import com.ikt.main.to.parser.TruckActivitiesParser;
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
 * Created by Arifin on 3/15/16.
 */
public class TruckAcitivitiesFragment extends Fragment implements IHttpResponse, TapView {

    @Bind(R.id.listTruckActivities)
    RecyclerView listTruckActivities;

    private TruckActivitiesAdapter adapter;
    private ArrayList<TruckActivitiesObject> data;
    private String orgId, session, userId;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public TruckAcitivitiesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.truck_activities_layout, container, false);
        ButterKnife.bind(this, rootView);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, getActivity());
        session = PreferenceManagers.getData(Config.KEY_SESSION, getActivity());
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, getActivity());
        listTruckActivities.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listTruckActivities.setLayoutManager(layoutManager);

        data = new ArrayList<TruckActivitiesObject>();
        adapter = new TruckActivitiesAdapter(getActivity(), data);
        adapter.setListener(this);
        listTruckActivities.setAdapter(adapter);
        getStatus();
        return rootView;
    }

    private void getStatus() {
        ArrayList<TicketObjectModel> ticketObjects = VectorModel.getInstance().getAssignTicketObjects();
        HttpTask task = new HttpTask(getActivity(), Config.URL_GET_TRUCK_ACTIVITIES, 1001, Config.POST, this, TruckActivitiesParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        //post.add(new BasicNameValuePair(Config.KEY_LAST_ID, ticketObjects.size() + ""));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        post.add(new BasicNameValuePair("time", "31-MAR-2015 15:59:40.000000"));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(getActivity());
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof TruckActivitiesParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ArrayList<TruckActivitiesObject> arrData = VectorModel.getInstance().getTruckActivitiesObjects();
                if (arrData != null) {
                    data.clear();
                    data.addAll(arrData);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        Toast.makeText(getActivity(), parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(Context context, int pid) {

    }

    @Override
    public void setAction(int type, int position, String name) {
        TruckActivitiesObject activitiesObject = data.get(position);

        Intent i = new Intent(getActivity(), TruckActivitiesDetailActivity.class);
        i.putExtra("position", position);
        i.putExtra("visitId", name);
        i.putExtra("driver", activitiesObject.getDriver());
        i.putExtra("isAssign", true);
        i.putExtra("truck", activitiesObject.getTruck());
        i.putExtra("desc", "-");

        getActivity().startActivity(i);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            getStatus();
            return true;
        } else if (item.getItemId() == R.id.action_search) {
            return false;
        }

        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_my_account).setVisible(false);
        menu.findItem(R.id.action_notif).setVisible(false);
        menu.findItem(R.id.action_language).setVisible(false);
        menu.findItem(R.id.action_refresh).setVisible(true);
        menu.findItem(R.id.action_sign_out).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            SearchView.SearchAutoComplete theTextArea = (SearchView.SearchAutoComplete) searchView
                    .findViewById(R.id.search_src_text);
            theTextArea.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
            theTextArea.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String query) {
                    Log.i("onQueryTextChange", query);
                    ArrayList<TruckActivitiesObject> arrDatas = VectorModel.getInstance().getTruckActivitiesObjects();
                    if (arrDatas != null) {
                        if (query != null && query.length() > 0) {
                            ArrayList<TruckActivitiesObject> arrFilter = new ArrayList<TruckActivitiesObject>();
                            for (TruckActivitiesObject to : arrDatas) {
                                if (to.getTruck().contains(query)) {
                                    arrFilter.add(to);
                                }
                            }
                            data.clear();
                            data.addAll(arrFilter);
                        } else {
                            data.clear();
                            data.addAll(arrDatas);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
