package com.ikt.main.to.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikt.main.to.R;
import com.ikt.main.to.activities.LanguageActivity;
import com.ikt.main.to.activities.MyAccountActivity;
import com.ikt.main.to.activities.NotificationSettingActivity;
import com.ikt.main.to.adapter.SettingAdapter;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/8/16.
 */
public class SettingFragment extends Fragment implements TapView{

    @Bind(R.id.listSetting)
    RecyclerView listSetting;

    private SettingAdapter adapter;
    private String[] data;

    public SettingFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.setting_layout, container, false);
        ButterKnife.bind(this, rootView);
        listSetting.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listSetting.setLayoutManager(layoutManager);

        data = getActivity().getResources().getStringArray(R.array.list_menu_setting);
        adapter = new SettingAdapter(getActivity(),data);
        adapter.setListener(this);
        listSetting.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setAction(int type, int position, String name) {
        Intent i = null;
        switch (position){
            case 0:
                i = new Intent(getActivity(), MyAccountActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(getActivity(), NotificationSettingActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(getActivity(), LanguageActivity.class);
                startActivity(i);
                break;
            case 3:
                Utility.createDialogLogout(getActivity(),getActivity().getResources().getString(R.string.are_you_sure_logout));
                break;
        }
    }
}
