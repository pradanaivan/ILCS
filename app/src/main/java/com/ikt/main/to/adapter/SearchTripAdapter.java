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

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckObject;

import java.util.ArrayList;

/**
 * Created by arifins on 10/10/16.
 */

public class SearchTripAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater = null;
    private ArrayList<String> tripObj;
    private TapView listener;

    public SearchTripAdapter(Activity activity, ArrayList<String> tripObj, TapView listener) {
        this.activity = activity;
        this.tripObj = tripObj;
        this.listener = listener;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tripObj.size();
    }

    @Override
    public Object getItem(int i) {
        return tripObj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
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

        final String trip = tripObj.get(position);
        holder.txtName.setText(trip);
        holder.txtPhone.setVisibility(View.GONE);
        holder.imgPP.setVisibility(View.GONE);
        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(1, position, trip);
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
