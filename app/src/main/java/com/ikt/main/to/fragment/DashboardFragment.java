package com.ikt.main.to.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ListAnnouncementAdapter;
import com.ikt.main.to.object.TruckAnnouncementObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/9/16.
 */
public class DashboardFragment extends Fragment {

    @Bind(R.id.txtTotal1)
    TextView txtTotal1;
    @Bind(R.id.txt_title1)
    TextView txtTitle1;
    @Bind(R.id.txtTotal2)
    TextView txtTotal2;
    @Bind(R.id.txt_title2)
    TextView txtTitle2;
    @Bind(R.id.txtTotal3)
    TextView txtTotal3;
    @Bind(R.id.txt_title3)
    TextView txtTitle3;
    @Bind(R.id.txtTotal4)
    TextView txtTotal4;
    @Bind(R.id.txt_title4)
    TextView txtTitle4;
    @Bind(R.id.content_list)
    RecyclerView contentList;
    @Bind(R.id.dashboard_layout)
    LinearLayout dashboardLayout;

    private ListAnnouncementAdapter adapter;
    private ArrayList<TruckAnnouncementObject> data;

    public DashboardFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard_layout, container, false);
        ButterKnife.bind(this, rootView);

        contentList.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        contentList.setLayoutManager(mLayoutManager);

        data = new ArrayList<TruckAnnouncementObject>();
        int i=0;
        while (i < 5){
            TruckAnnouncementObject object = new TruckAnnouncementObject();
            object.setPlatNo("B 123"+i+" TAM");
            object.setStatus("Announced");
            object.setTimeSlot("1" + i + ":00");
            data.add(object);
            i++;
        }
        adapter = new ListAnnouncementAdapter(getActivity(),data);
        contentList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
