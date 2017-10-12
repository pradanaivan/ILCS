package com.ikt.main.to.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.DriverObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 2/8/16.
 */
public class SearchDriverAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater = null;
    private ArrayList<DriverObject> driverObj;
    private TapView listener;

    public SearchDriverAdapter(Activity activity, ArrayList<DriverObject> driverObj, TapView listener) {
        this.activity = activity;
        this.driverObj = driverObj;
        this.listener = listener;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return driverObj.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        Holder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.row_item_search, null);
            holder = new Holder();
            holder.txtName = (TextView) vi.findViewById(R.id.txtName);
            holder.txtPhone = (TextView) vi.findViewById(R.id.txtPhone);
            holder.imgPP = (ImageView) vi.findViewById(R.id.pp_driver);
            holder.list_item = (LinearLayout) vi.findViewById(R.id.list_item);
            vi.setTag(holder);
        } else {
            holder = (Holder) vi.getTag();
        }

        DriverObject driver = driverObj.get(position);
        final String name = driver.getDriverName();
        holder.txtName.setText(driver.getDriverName());
        holder.txtPhone.setText(driver.getDriverPhone());
        Glide.with(activity).load(driver.getUrlImage()).placeholder(R.drawable.place_holder).crossFade().into(holder.imgPP);
        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(1,position,name);
            }
        });
        return vi;
    }

    public class Holder {
        TextView txtName;
        TextView txtPhone;
        ImageView imgPP;
        LinearLayout list_item;
    }
}
