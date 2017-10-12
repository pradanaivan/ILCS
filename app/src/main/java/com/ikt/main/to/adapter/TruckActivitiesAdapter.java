package com.ikt.main.to.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckActivitiesObject;
import com.ikt.main.to.util.Utility;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/15/16.
 */
public class TruckActivitiesAdapter extends RecyclerView.Adapter<TruckActivitiesAdapter.ViewHolder> {

    private ArrayList<TruckActivitiesObject> data;
    private Activity activity;
    private TapView listener;
    private TruckActivitiesObject obj;

    public TruckActivitiesAdapter(Activity activity, ArrayList<TruckActivitiesObject> data){
        super();
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View rootView = LayoutInflater.from(activity).inflate(R.layout.row_truck_activities_layout,parent,false);
        View rootView = LayoutInflater.from(activity).inflate(R.layout.row_truck_activities_layout_new,parent,false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        obj = data.get(position);
        holder.txtTruckAndDriver.setText(obj.getTruck()+" | "+ obj.getDriver());
//        holder.txtTimeSlot.setText(obj.getLast_update()+"");
        holder.txtStatus.setText(obj.getLast_status());
        // Satrio
        String time = obj.getLast_update()+"";
        holder.txtDate.setText(time.substring(0,10));
        holder.txtHour.setText(time.substring(12,17));
        holder.truckActivitiesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(position, position, obj.getVisitId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTruckAndDriver;
//        private TextView txtTimeSlot;
        private TextView txtStatus;
        private ImageView iconArrow;
        private LinearLayout truckActivitiesLayout;
        // Satrio
        private TextView txtDate;
        private TextView txtHour;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTruckAndDriver = (TextView) itemView.findViewById(R.id.txtTruckAndDriver);
//            txtTimeSlot = (TextView) itemView.findViewById(R.id.txtTimeSlot);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            iconArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            truckActivitiesLayout = (LinearLayout) itemView.findViewById(R.id.rowTruckActivities);
            // Satrio
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtHour = (TextView) itemView.findViewById(R.id.txtHour);
        }
    }

    public void setListener(TapView listener){
        this.listener = listener;
    }
}
