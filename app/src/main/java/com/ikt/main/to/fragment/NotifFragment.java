package com.ikt.main.to.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.NotifDetailActivity;
import com.ikt.main.to.activities.StatusTicketDetailActivity;
import com.ikt.main.to.activities.TruckActivitiesDetailActivity;
import com.ikt.main.to.adapter.NotificationAdapter;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.NotifObject;
import com.ikt.main.to.util.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 5/1/16.
 */
public class NotifFragment extends Fragment implements TapView{


    @Bind(R.id.listNotif)
    ListView listNotif;
    @Bind(R.id.notif_layout)
    LinearLayout notifLayout;

    private NotificationAdapter adapter;
    private ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
    private DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notif_layout, container, false);
        ButterKnife.bind(this, view);
        dbHelper = new DBHelper(getActivity());
        adapter = new NotificationAdapter(getActivity(), listItems, this);
        listNotif.setAdapter(adapter);
        setData();

        return view;
    }

    private void setData() {
        List<NotifObject> datas = dbHelper.getAllNotif();
        listItems.clear();
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                NotifObject notifObject = datas.get(i);
                String msg = notifObject.getMessage();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Config.KEY_NAME, msg);
                map.put(Config.KEY_ID, notifObject.getId()+"");
                map.put(Config.KEY_TYPE, notifObject.getType()+"");
                listItems.add(map);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        HashMap<String, String> song = listItems.get(position);
//        String msg = song.get(Config.KEY_NAME);
//        String[] messages = msg.split("#");
//        int code = Integer.parseInt(messages[0]);
//        if(code == 40 || code == 50){
//            Intent i = new Intent(getActivity(), NotifDetailActivity.class);
//            i.putExtra("msg", msg);
//            i.putExtra("msgId", messages[1]);
//            startActivity(i);
//        }
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_refresh).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            setData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAction(int type, int position, String name) {
        final HashMap<String, String> song = listItems.get(position);
        if(type == 0) {
            String msg = song.get(Config.KEY_NAME);
            String[] messages = msg.split("#");
            int code = Integer.parseInt(messages[0]);
            Intent i = null;
            switch (code){
                case 40:
                case 50:
                    i = new Intent(getActivity(), NotifDetailActivity.class);
                    i.putExtra("msg", msg);
                    i.putExtra("msgId", messages[1]);
                    getActivity().startActivity(i);
                    break;
                case 10:
                    i = new Intent(getActivity(), StatusTicketDetailActivity.class);
                    i.putExtra("position", position);
                    i.putExtra("visitId", messages[2]);
                    i.putExtra("isAssign", false);
                    getActivity().startActivity(i);
                    break;
                case 20:
                    i = new Intent(getActivity(), StatusTicketDetailActivity.class);
                    i.putExtra("position", position);
                    i.putExtra("visitId", messages[2]);
                    i.putExtra("isAssign", true);
                    getActivity().startActivity(i);
                    break;
                case 31:
                case 32:
                case 33:
                    i = new Intent(getActivity(), TruckActivitiesDetailActivity.class);
                    i.putExtra("position", position);
                    i.putExtra("visitId", messages[4]);
                    i.putExtra("driver", messages[5]);
                    i.putExtra("isAssign", true);
                    i.putExtra("truck", messages[1]);
                    i.putExtra("desc", "-");
                    getActivity().startActivity(i);
                    break;
            }
        }else{
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            deleteMessage(song.get(Config.KEY_ID));
                            dialog.dismiss();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.dismiss();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
            builder.setMessage(getActivity().getResources().getString(R.string.confirm_delete)).setPositiveButton(getActivity().getResources().getString(R.string.yes),
                    dialogClickListener).setNegativeButton(getActivity().getResources().getString(R.string.no), dialogClickListener).show();
        }
    }

    private void deleteMessage(String id){
        dbHelper.deleteNotifById(id);
        setData();
    }
}
