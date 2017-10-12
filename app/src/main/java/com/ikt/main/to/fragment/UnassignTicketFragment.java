package com.ikt.main.to.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.StatusTicketDetailActivity;
import com.ikt.main.to.adapter.MainStatusTicketAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.TicketObject;
import com.ikt.main.to.parser.TickerParser;
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
 * Created by Arifin on 3/14/16.
 */
public class UnassignTicketFragment extends Fragment implements TapView, IHttpResponse {


    @Bind(R.id.mainStatusTicketLayout)
    RecyclerView mainStatusTicketLayout;

    private MainStatusTicketAdapter adapter;
    private ArrayList<TicketObject> data;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String orgId, session, userId;
    
    public UnassignTicketFragment(){
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_status_ticket_layout, container, false);
        ButterKnife.bind(this, rootView);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, getActivity());
        session = PreferenceManagers.getData(Config.KEY_SESSION, getActivity());
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, getActivity());
        mainStatusTicketLayout.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mainStatusTicketLayout.setLayoutManager(layoutManager);

        data = new ArrayList<TicketObject>();

        adapter = new MainStatusTicketAdapter(getActivity(),data);
        adapter.setListener(this);
        mainStatusTicketLayout.setAdapter(adapter);
        setData();
        return rootView;
    }

    private void setData(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<TicketObject> arrDatas = VectorModel.getInstance().getListUnassignTicketObjects();
                if(arrDatas != null){
                    data.clear();
                    data.addAll(arrDatas);
                }
                adapter.notifyDataSetChanged();
            }
        },300);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getTickets();
//        ArrayList<TicketObject> arrDatas = VectorModel.getInstance().getListUnassignTicketObjects();
//        if(arrDatas != null){
//            data.clear();
//            data.addAll(arrDatas);
//        }
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void setAction(int type, int position, String name) {
        TicketObject obj = data.get(position);
        Intent i = new Intent(getActivity(), StatusTicketDetailActivity.class);
        i.putExtra("position", position);
        i.putExtra("visitId", obj.getVisitId());
        i.putExtra("isAssign",true);
        getActivity().startActivity(i);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchItem.expandActionView();
            searchView = (SearchView) searchItem.getActionView();
            SearchView.SearchAutoComplete theTextArea = (SearchView.SearchAutoComplete) searchView
                    .findViewById(R.id.search_src_text);
            theTextArea.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        }
        if (searchView != null) {
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String query) {
                    Log.i("onQueryTextChange", query);
                    ArrayList<TicketObject> arrDatas = VectorModel.getInstance().getListUnassignTicketObjects();
                    if(arrDatas != null){
                        if (query!=null && query.length()>0) {
                            ArrayList<TicketObject> arrFilter = new ArrayList<TicketObject>();
                            for (TicketObject to : arrDatas) {
                                if (to.getVisitId().toLowerCase().contains(query.toLowerCase())) {
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
            searchView.onActionViewExpanded();
            final int textViewID = searchView.getResources().getIdentifier("android:id/search_src_text",null, null);
            final AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(textViewID);
            try {
                Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
                mCursorDrawableRes.setAccessible(true);
                mCursorDrawableRes.set(searchTextView, R.drawable.color_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
            } catch (Exception e) {}
            changeSearchViewTextColor(searchView);
        }
        super.onCreateOptionsMenu(menu, inflater);
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
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_refresh).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==  R.id.action_refresh) {
            getTickets();
			return true;
        } else if (item.getItemId() == R.id.action_search) {
            return false;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    private void getTickets() {
        ArrayList<TicketObject> ticketObjects = VectorModel.getInstance().getListUnassignTicketObjects();
        HttpTask task = new HttpTask(getActivity(), Config.URL_GET_TICKET, 1001, Config.POST, this, TickerParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair(Config.KEY_ORG_ID, orgId));
        post.add(new BasicNameValuePair(Config.KEY_LAST_ID, ticketObjects.size() + ""));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, session));
        post.add(new BasicNameValuePair(Config.KEY_USER_ID, userId));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(getActivity());
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof TickerParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                ArrayList<TicketObject> arrDatas = VectorModel.getInstance().getListUnassignTicketObjects();
                if (arrDatas != null) {
                    data.clear();
                    data.addAll(arrDatas);
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
}
