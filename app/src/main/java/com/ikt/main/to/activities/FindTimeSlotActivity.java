package com.ikt.main.to.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifins on 3/22/17.
 */

public class FindTimeSlotActivity extends AppCompatActivity {

    @Bind(R.id.edtTgl)
    MaterialEditText edtTgl;
    @Bind(R.id.spinDate)
    Spinner spinDate;
    @Bind(R.id.llSpinDate)
    LinearLayout llSpinDate;
    @Bind(R.id.spinSlot)
    Spinner spinSlot;
    @Bind(R.id.llSpinSlot)
    LinearLayout llSpinSlot;
    @Bind(R.id.spinArea)
    Spinner spinArea;
    @Bind(R.id.llSpinArea)
    LinearLayout llSpinArea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeslot_pilihan_layout);
        ButterKnife.bind(this);



    }
}
