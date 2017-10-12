package com.ikt.main.to.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.AddDriverActivity;
import com.ikt.main.to.adapter.DriverAdapter;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.component.SwipeMenuListView;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.controller.SwipeMenuCreator;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.SwipeMenu;
import com.ikt.main.to.object.SwipeMenuItem;
import com.ikt.main.to.parser.DriverParser;
import com.ikt.main.to.parser.SimpleParser;
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
 * Created by Arifin on 3/19/16.
 */
public class DriverFragment extends Fragment implements View.OnClickListener, TapView, SwipeMenuListView.OnMenuItemClickListener, IHttpResponse {

    @Bind(R.id.listView)
    SwipeMenuListView listView;
    @Bind(R.id.txtSearch)
    MaterialEditText txtSearch;
    @Bind(R.id.btnAddDriver)
    Button btnAddDriver;

    private String[] nameList = {"Rahman", "Rohim", "Budi", "Nicko", "Nicky", "Surya", "Sandy", "Nurrohman"};
    private String[] phoneList = {"0812345666", "08646467575", "086464675732", "08642367575", "08646461234", "0864609879", "0864645432", "08646467890"};
    private DriverAdapter adapter;
    private ArrayList<DriverObject> drivers;
    private DBHelper dbHelper;
    private String orgId;
    private String query;
    private int indexDriver;
    private String lastId;


    public DriverFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.driver_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        orgId = PreferenceManagers.getData(Config.KEY_ORG_ID, getActivity());
        lastId = PreferenceManagers.getData(Config.KEY_LAST_ID, getActivity());
        dbHelper = new DBHelper(getActivity());
        btnAddDriver.setOnClickListener(this);
        drivers = new ArrayList<DriverObject>();
        int count = dbHelper.getDriverCount();
        if (count > 0) {
            List<DriverObject> arrDriver = dbHelper.getAllDriver();
            for (int i = 0; i < arrDriver.size(); i++) {
                DriverObject driver = arrDriver.get(i);
                drivers.add(driver);
            }
        }
        adapter = new DriverAdapter(getActivity(), drivers, this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creatorMenu = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                createMenu(menu);
            }
        };
        listView.setMenuCreator(creatorMenu);
        listView.setOnMenuItemClickListener(this);

        txtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        txtSearch.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            onSearchAction();
                            return true;
                        }
                        return false;
                    }
                });
        return rootView;
    }

    private void createMenu(SwipeMenu menu) {
        SwipeMenuItem editMenu = new SwipeMenuItem(getActivity());
        editMenu.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
        editMenu.setWidth(dp2px(90));
        editMenu.setTitle("Edit");
        editMenu.setTitleColor(Color.WHITE);
        editMenu.setTitleSize(18);
        menu.addMenuItem(editMenu);

        SwipeMenuItem deleteMenu = new SwipeMenuItem(getActivity());
        deleteMenu.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18, 0x5E)));
        deleteMenu.setWidth(dp2px(90));
        deleteMenu.setIcon(R.drawable.ic_delete);
        menu.addMenuItem(deleteMenu);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void onSearchAction() {
        Utility.hideKeyboard(getActivity());
        String query = txtSearch.getText().toString();
        int count = dbHelper.getDriverCount();
        if (count > 0) {
            drivers.clear();
            List<DriverObject> arrDriver = dbHelper.getDriverByName(query);
            for (int i = 0; i < arrDriver.size(); i++) {
                DriverObject driver = arrDriver.get(i);
                drivers.add(driver);
            }
            adapter.notifyDataSetChanged();
        } else {
            this.query = query;
            getDrivers();
        }
    }

    private void getDrivers() {
        HttpTask task = new HttpTask(getActivity(), Config.URL_DRIVER, 1001, Config.POST, this, DriverParser.class);
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

    private void deleteDrivers(String id) {
        HttpTask task = new HttpTask(getActivity(), Config.URL_DELETE_DRIVER, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("driver_id", id));
        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        DriverObject driverObject = drivers.get(position);
        switch (index) {
            case 0:
                Intent i = new Intent(getActivity(), AddDriverActivity.class);
                i.putExtra("data", driverObject);
                startActivity(i);
                break;
            case 1:
                Utility.createDialogDeleteDriver(getActivity(), getString(R.string.are_you_sure_delete), position, this);
                break;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddDriver) {
            Intent i = new Intent(getActivity(), AddDriverActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void setAction(int type, int position, String name) {
        if (name.equalsIgnoreCase("delete")) {
            indexDriver = position;
            DriverObject driverObject = drivers.get(position);
            deleteDrivers(driverObject.getDriverId());
        } else {
            DriverObject driver = drivers.get(position);
            Intent i = new Intent(getActivity(), AddDriverActivity.class);
            i.putExtra("data", driver);
            getActivity().startActivity(i);
        }
    }

    @Override
    public void onStarted(Context context, int pid) {
        Utility.hideKeyboard(getActivity());
    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof DriverParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                List<DriverObject> arrDriver = dbHelper.getDriverByName(query);
                drivers.clear();
                for (int i = 0; i < arrDriver.size(); i++) {
                    DriverObject driver = arrDriver.get(i);
                    drivers.add(driver);
                }
                adapter.notifyDataSetChanged();
            }
        } else if (result instanceof SimpleParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                DriverObject driverObject = drivers.get(indexDriver);
                dbHelper.deleteDriverById(driverObject.getId());
                drivers.clear();
                List<DriverObject> arrDriver = dbHelper.getAllDriver();
                for (int i = 0; i < arrDriver.size(); i++) {
                    DriverObject driver = arrDriver.get(i);
                    drivers.add(driver);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), getString(R.string.success_message_delete_driver), Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            getDrivers();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_refresh).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(false);
    }
}
