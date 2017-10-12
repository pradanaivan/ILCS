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
import com.ikt.main.to.object.TicketObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/14/16.
 */
public class MainStatusTicketAdapter extends RecyclerView.Adapter<MainStatusTicketAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<TicketObject> data;
    private TapView listener;
    private TicketObject obj;

    public MainStatusTicketAdapter(Activity activity, ArrayList<TicketObject> data) {
        super();
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRoot = LayoutInflater.from(activity).inflate(R.layout.status_entry_ticket_layout, parent, false);
        ViewHolder holder = new ViewHolder(viewRoot);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        obj = data.get(position);
        holder.txtTruckAndDriver.setText(obj.getPlatNo()+" | "+ obj.getDriverName() +" | ("+obj.getType()+")");
        holder.txtTimeSlot.setText(obj.getBegin());
        holder.txtTicketCode.setText(obj.getVisitId());
        final String visitId = obj.getVisitId();
        holder.statusEntryTicketLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(position,position,visitId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTruckAndDriver;
        private TextView txtTimeSlot;
        private TextView txtTicketCode;
        private ImageView iconArrow;
        private LinearLayout statusEntryTicketLayout;

        private ViewHolder(View viewItem) {
            super(viewItem);
            txtTruckAndDriver = (TextView) viewItem.findViewById(R.id.txtTruckAndDriver);
            txtTimeSlot = (TextView) viewItem.findViewById(R.id.txtTimeSlot);
            txtTicketCode = (TextView) viewItem.findViewById(R.id.txtTicketCode);
            iconArrow = (ImageView) viewItem.findViewById(R.id.imgArrow);
            statusEntryTicketLayout = (LinearLayout) viewItem.findViewById(R.id.statusEntryTicketLayout);
        }
    }

    public void setListener(TapView listener){
        this.listener = listener;
    }
}
