package com.ikt.main.to.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckActivitiesDetailObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/15/16.
 */
public class TruckActivitiesDetailAdapter extends RecyclerView.Adapter<TruckActivitiesDetailAdapter.ViewHolder> {

    private ArrayList<TruckActivitiesDetailObject> data;
    private Activity activity;
    private TapView listener;
    private TruckActivitiesDetailObject obj;

    public TruckActivitiesDetailAdapter(Activity activity, ArrayList<TruckActivitiesDetailObject> data) {
        super();
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(activity).inflate(R.layout.row_truck_activities_detail_layout, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        obj = data.get(position);
        holder.txtTRK.setText(obj.getVisitId());

        if (null == obj.getDriver() || obj.getDriver().equalsIgnoreCase("null")) {
            holder.txtVia.setText(activity.getResources().getText(R.string.label_via_web));
        } else {
            holder.txtVia.setText(activity.getResources().getText(R.string.label_via_mobile));
        }

        if (!obj.getAnnounce().equalsIgnoreCase("null")) {
            Drawable drAnnounce = ContextCompat.getDrawable(activity, R.drawable.step_1_success);
            holder.imgAnnounce.setImageDrawable(drAnnounce);
            holder.txtDate.setText(obj.getAnnounce());
            holder.txtStatus.setText(activity.getResources().getText(R.string.label_ANNOUNCE));
            holder.imgAnnounce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txtDate.setText(obj.getAnnounce());
                    holder.txtStatus.setText(activity.getResources().getText(R.string.label_ANNOUNCE));
                    holder.footer_layout.setVisibility(View.VISIBLE);
                    listener.setAction(1, position, "announced");
                }
            });
//            holder.imgAnnounce.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                    if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                        holder.footer_layout.setVisibility(View.VISIBLE);
//                        return true;
//                    } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
//                        holder.footer_layout.setVisibility(View.GONE);
//                        return true;
//                    }
//                    return false;
//                }
//            });

        }

        if (!obj.getOperation().equalsIgnoreCase("null")) {
            Drawable drOperation = ContextCompat.getDrawable(activity, R.drawable.step_2_success);
            holder.imgOperation.setImageDrawable(drOperation);
            holder.txtDate.setText(obj.getOperation());
            holder.txtStatus.setText(activity.getResources().getText(R.string.label_OPERATION));
            holder.imgOperation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txtDate.setText(obj.getOperation());
                    holder.txtStatus.setText(activity.getResources().getText(R.string.label_OPERATION));
                    holder.footer_layout.setVisibility(View.VISIBLE);
                    listener.setAction(1, position, "operation");
                }
            });
//            holder.imgOperation.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                    if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                        holder.footer_layout.setVisibility(View.VISIBLE);
//                        return true;
//                    } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
//                        holder.footer_layout.setVisibility(View.GONE);
//                        return true;
//                    }
//                    return false;
//                }
//            });
        }


        if (!obj.getComplete().equalsIgnoreCase("null")) {
            Drawable drComplete = ContextCompat.getDrawable(activity, R.drawable.step_3_success);
            holder.imgComplete.setImageDrawable(drComplete);
            holder.txtDate.setText(obj.getComplete());
            holder.txtStatus.setText(activity.getResources().getText(R.string.label_COMPLETE));
            holder.imgComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.txtDate.setText(obj.getComplete());
                    holder.txtStatus.setText(activity.getResources().getText(R.string.label_COMPLETE));
                    holder.footer_layout.setVisibility(View.VISIBLE);
                    listener.setAction(1, position, "complete");
                }
            });
//            holder.imgComplete.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                    if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                        holder.footer_layout.setVisibility(View.VISIBLE);
//                        return true;
//                    } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
//                        holder.footer_layout.setVisibility(View.GONE);
//                        return true;
//                    }
//                    return false;
//                }
//            });
        }


        if (!obj.getLeft().equalsIgnoreCase("null")) {
            Drawable drLeft = ContextCompat.getDrawable(activity, R.drawable.step_4_success);
            holder.imgLeft.setImageDrawable(drLeft);
            holder.txtDate.setText(obj.getLeft());
            holder.txtStatus.setText(this.activity.getResources().getText(R.string.label_LEFT));
            holder.imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.footer_layout.setVisibility(View.VISIBLE);
                    listener.setAction(1, position, "left");
                }
            });
//            holder.imgLeft.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                    if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                        holder.footer_layout.setVisibility(View.VISIBLE);
//                        return true;
//                    } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
//                        holder.footer_layout.setVisibility(View.GONE);
//                        return true;
//                    }
//                    return false;
//                }
//            });
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setListener(TapView listener) {
        this.listener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtStatus;
        private TextView txtDate;
        private TextView txtVia;
        private TextView txtTRK;
        private ImageView imgAnnounce;
        private ImageView imgOperation;
        private ImageView imgComplete;
        private ImageView imgLeft;
        private RelativeLayout truckActivitiesLayout;
        private TableLayout footer_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtVia = (TextView) itemView.findViewById(R.id.txtVia);
            txtTRK = (TextView) itemView.findViewById(R.id.txtTRK);
            imgAnnounce = (ImageView) itemView.findViewById(R.id.imgAnnounce);
            imgOperation = (ImageView) itemView.findViewById(R.id.imgOperation);
            imgComplete = (ImageView) itemView.findViewById(R.id.imgComplete);
            imgLeft = (ImageView) itemView.findViewById(R.id.imgLeft);

            truckActivitiesLayout = (RelativeLayout) itemView.findViewById(R.id.rowTruckActivities);
            footer_layout = (TableLayout) itemView.findViewById(R.id.footer_layout);
        }
    }
}
