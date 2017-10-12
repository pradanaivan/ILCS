package com.ikt.main.to.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TimeSlotObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/15/16.
 */
public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<TimeSlotObject> data;
    private TapView listener;
    private TimeSlotObject obj;

    public TimeSlotAdapter(Activity activity,ArrayList<TimeSlotObject> data){
        super();
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.row_time_slot,parent,false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        obj = data.get(position);
        holder.txtTitle.setText(obj.getTimeSlotBookDate()+" - "+obj.getTimeSlotCode()+" - "+obj.getTimeSlotPosition());
//        holder.txtAvailable.setText(obj.getAvailable());
        holder.rowTimeSlotLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(position, position, obj.getTimeSlotBookDate()+" - "+obj.getTimeSlotCode()+" - "+obj.getTimeSlotPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private TextView txtAvailable;
        private RelativeLayout rowTimeSlotLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtAvailable = (TextView) itemView.findViewById(R.id.txtAvailable);
            rowTimeSlotLayout = (RelativeLayout) itemView.findViewById(R.id.rowTimeSlotLayout);
        }
    }

    public void setListener(TapView listener){
        this.listener = listener;
    }
}
