package com.ikt.main.to.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.object.TruckAnnouncementObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/9/16.
 */
public class ListAnnouncementAdapter extends RecyclerView.Adapter<ListAnnouncementAdapter.ViewHolder> {

    private Activity act;
    private ArrayList<TruckAnnouncementObject> data;

    public ListAnnouncementAdapter(Activity act, ArrayList<TruckAnnouncementObject> data){
        super();
        this.act = act;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_announcement_dashboard_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TruckAnnouncementObject truckAnnouncementObject = data.get(position);
        holder.txtStatus.setText(truckAnnouncementObject.getStatus());
        holder.txtTimeSlot.setText(truckAnnouncementObject.getTimeSlot());
        holder.txtTruckPlatNo.setText(truckAnnouncementObject.getPlatNo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTimeSlot;
        public TextView txtTruckPlatNo;
        public TextView txtStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            txtTimeSlot = (TextView) itemView.findViewById(R.id.txtTimeSlot);
            txtTruckPlatNo = (TextView) itemView.findViewById(R.id.txtTruckPlateNo);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);

        }
    }
}
