package com.ikt.main.to.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ikt.main.to.model.TicketObjectModel;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.HomeActivity;
import com.ikt.main.to.component.CircleMenuLayout;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
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
 * Created by Arifin on 4/10/16.
 */
public class CircleMenuFragment extends Fragment implements IHttpResponse {

    @Bind(R.id.id_circle_menu_item_center)
    RelativeLayout idCircleMenuItemCenter;
    @Bind(R.id.id_menulayout)
    CircleMenuLayout idMenulayout;

    private String[] titles;
    private int[] icons;
    private TypedArray images;
    private String orgId,session,userId;

    public CircleMenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circle_menu_layout, container, false);
        ButterKnife.bind(this, rootView);
        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, getActivity());
        session = PreferenceManagers.getData(Config.KEY_SESSION, getActivity());
        userId = PreferenceManagers.getData(Config.KEY_USER_ID, getActivity());
        titles = getActivity().getResources().getStringArray(R.array.title_main_menu);
        images = getActivity().getResources().obtainTypedArray(R.array.icon_main_menu);
        icons = new int[titles.length];
        for (int i = 0; i < titles.length; i++) {
            int resourceId = images.getResourceId(i, 0);
            icons[i] = resourceId;
        }

        idMenulayout.setMenuItemIconsAndTexts(icons, titles);

        idMenulayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {
                onItemClick(pos);
//                Toast.makeText(getActivity(), titles[pos],
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void itemCenterClick(View view) {
//                Toast.makeText(getActivity(),
//                        "you can do something just like ccb  ",
//                        Toast.LENGTH_SHORT).show();

            }
        });
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

    private void onItemClick(int pos){
//        Toast.makeText(getActivity(),"position : "+pos,Toast.LENGTH_SHORT).show();
        Fragment fr = null;
        switch (pos){
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
