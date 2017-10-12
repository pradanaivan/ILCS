package com.ikt.main.to.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ikt.main.to.R;
import com.ikt.main.to.activities.IncomingVinActivity;
import com.ikt.main.to.activities.OutgoingActivity;
import com.ikt.main.to.activities.SearchDriverActivity;
import com.ikt.main.to.activities.SearchTruckActivity;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.TruckObject;
import com.ikt.main.to.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/8/16.
 */
public class AnnouncementFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.edtDriverName)
    MaterialEditText edtDriverName;
    @Bind(R.id.edtDriverPhone)
    MaterialEditText edtDriverPhone;
    @Bind(R.id.edtPlatNo)
    MaterialEditText edtPlatNo;
    @Bind(R.id.rdInternational)
    AppCompatRadioButton rdInternational;
    @Bind(R.id.rdDomestic)
    AppCompatRadioButton rdDomestic;
    @Bind(R.id.rdGroup)
    RadioGroup rdGroup;
    @Bind(R.id.btnIncoming)
    Button btnIncoming;
    @Bind(R.id.btnOutgoing)
    Button btnOutgoing;
    @Bind(R.id.btnBackload)
    Button btnBackload;


    private String driverId, platNoCode;

    public AnnouncementFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem account = menu.findItem(R.id.action_my_account);
        account.setVisible(false);
        MenuItem notif = menu.findItem(R.id.action_notif);
        notif.setVisible(false);
        MenuItem language = menu.findItem(R.id.action_language);
        language.setVisible(false);
        MenuItem refresh = menu.findItem(R.id.action_refresh);
        refresh.setVisible(false);
        MenuItem sign_out = menu.findItem(R.id.action_sign_out);
        sign_out.setVisible(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.announcement_layout, container, false);
        ButterKnife.bind(this, rootView);
        edtDriverName.setOnClickListener(this);
        edtPlatNo.setOnClickListener(this);
        btnIncoming.setOnClickListener(this);
        btnOutgoing.setOnClickListener(this);
        btnBackload.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        String driverName = edtDriverName.getText().toString();
        if (v == edtDriverName) {
            Intent i = new Intent(getActivity(), SearchDriverActivity.class);
            startActivityForResult(i, 100);
        } else if (v == edtPlatNo) {
            Intent i = new Intent(getActivity(), SearchTruckActivity.class);
            startActivityForResult(i, 200);
        } else if (v == btnIncoming) {
            if(driverId != null && platNoCode != null) {
                int selectedId = rdGroup.getCheckedRadioButtonId();
                AppCompatRadioButton radioButton = (AppCompatRadioButton) getActivity().findViewById(selectedId);
                String check = radioButton.getText().toString();
                if (check.equalsIgnoreCase(getResources().getString(R.string.international))) {
                    selectedId = 0;
                } else if (check.equalsIgnoreCase(getResources().getString(R.string.domestic))) {
                    selectedId = 1;
                }
//            Intent i = new Intent(getActivity(), CheckingVinOrTripActivity.class);
//            Intent i = new Intent(getActivity(), IncomingActivity.class);
                Intent i = new Intent(getActivity(), IncomingVinActivity.class);
                i.putExtra("type", selectedId);
                i.putExtra("driver", driverName);
                i.putExtra("driverId", driverId);
                i.putExtra("platNo", platNoCode);
                i.putExtra("proses", 0);
                i.putExtra("title", "Incoming");
                startActivity(i);
            }else{
                Utility.validateEditText(edtDriverName, getActivity().getString(R.string.mandatory));
                Utility.validateEditText(edtPlatNo, getActivity().getString(R.string.mandatory));
            }
        } else if (v == btnOutgoing) {
            if(driverId != null && platNoCode != null) {
                int selectedId = rdGroup.getCheckedRadioButtonId();
                AppCompatRadioButton radioButton = (AppCompatRadioButton) getActivity().findViewById(selectedId);
                String check = radioButton.getText().toString();
                if (check.equalsIgnoreCase(getResources().getString(R.string.international))) {
                    selectedId = 0;
                } else if (check.equalsIgnoreCase(getResources().getString(R.string.domestic))) {
                    selectedId = 1;
                }
                Intent i = new Intent(getActivity(), OutgoingActivity.class);
                i.putExtra("type", selectedId);
                i.putExtra("driver", driverName);
                i.putExtra("driverId", driverId);
                i.putExtra("platNo", platNoCode);
                i.putExtra("proses", 1);
                startActivity(i);
            }else{
                Utility.validateEditText(edtDriverName, getActivity().getString(R.string.mandatory));
                Utility.validateEditText(edtPlatNo, getActivity().getString(R.string.mandatory));
            }
        } else if (v == btnBackload) {
            if(driverId != null && platNoCode != null) {
                int selectedId = rdGroup.getCheckedRadioButtonId();
                AppCompatRadioButton radioButton = (AppCompatRadioButton) getActivity().findViewById(selectedId);
                String check = radioButton.getText().toString();
                if (check.equalsIgnoreCase(getResources().getString(R.string.international))) {
                    selectedId = 0;
                } else if (check.equalsIgnoreCase(getResources().getString(R.string.domestic))) {
                    selectedId = 1;
                }
//            Intent i = new Intent(getActivity(), CheckingVinOrTripActivity.class);
//            Intent i = new Intent(getActivity(), IncomingActivity.class);
                Intent i = new Intent(getActivity(), IncomingVinActivity.class);
                i.putExtra("type", selectedId);
                i.putExtra("driver", driverName);
                i.putExtra("driverId", driverId);
                i.putExtra("platNo", platNoCode);
                i.putExtra("proses", 2);
                i.putExtra("title", "Backload");
                startActivity(i);
            }else{
                Utility.validateEditText(edtDriverName, getActivity().getString(R.string.mandatory));
                Utility.validateEditText(edtPlatNo, getActivity().getString(R.string.mandatory));
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 100) {
                Bundle arguments = data.getExtras();
                DriverObject driver = (DriverObject) arguments.getSerializable("data");
                driverId = driver.getDriverId();
                edtDriverName.setText(driver.getDriverName());
            } else if (requestCode == 200) {
                Bundle arguments = data.getExtras();
                TruckObject truck = (TruckObject) arguments.getSerializable("data");
                edtPlatNo.setText(truck.getPlatNo());
                platNoCode = truck.getPlatNoCode();
            }
        }

    }
}
