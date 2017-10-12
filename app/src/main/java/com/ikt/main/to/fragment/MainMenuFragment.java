package com.ikt.main.to.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ikt.main.to.model.TicketObjectModel;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.HomeActivity;
import com.ikt.main.to.adapter.GridAdapter;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.TapGrid;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.parser.TickerParser;
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
 * Created by Arifin on 2/8/16.
 */
public class MainMenuFragment extends Fragment implements TapGrid,IHttpResponse{

    @Bind(R.id.content_view)
    RecyclerView contentView;

    private GridAdapter mAdapter;
    private String orgId,session,userId;

    public MainMenuFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_menu_layout, container, false);
        ButterKnife.bind(this, rootView);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, getActivity());
        session = PreferenceManagers.getData(Config.KEY_SESSION, getActivity());
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, getActivity());
        contentView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        contentView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getActivity());
        mAdapter.setOnTapListener(this);
        contentView.setAdapter(mAdapter);
        getTickets();
        return rootView;
    }

    private void getTickets() {
        ArrayList<TicketObjectModel> ticketObjects = VectorModel.getInstance().getAssignTicketObjects();
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onTapItemGrid(int position) {
        Toast.makeText(getActivity(),"position : "+position,Toast.LENGTH_SHORT).show();
        Fragment fr = null;
        switch (position){
            case 0:
                fr = new AnnouncementFragment();
                ((HomeActivity) getActivity()).replaceFragment(fr);
                break;
            case 1:
                fr = new EntryTicketFragment();
                ((HomeActivity) getActivity()).replaceFragment(fr);
                break;
            case 2:
                fr = new TruckAcitivitiesFragment();
                ((HomeActivity) getActivity()).replaceFragment(fr);
                break;
            case 4:
                fr = new DriverFragment();
                ((HomeActivity) getActivity()).replaceFragment(fr);
                break;
//            case 7:
//                fr = new SettingFragment();
//                ((HomeActivity) getActivity()).replaceFragment(fr);
        }
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(getActivity());
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if(result instanceof TickerParser){
            IParser parser = (IParser) result;
            if(!parser.isError()){
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
