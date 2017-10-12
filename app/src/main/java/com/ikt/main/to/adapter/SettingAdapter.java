package com.ikt.main.to.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;

/**
 * Created by Arifin on 3/12/16.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private Activity activity;
    private String[] data;
    private TapView listener;

    public SettingAdapter(Activity activity, String[] data) {
        super();
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRoot = LayoutInflater.from(activity).inflate(R.layout.row_setting_layout, parent, false);
        ViewHolder holder = new ViewHolder(viewRoot);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String title = data[position];
        holder.txtTitle.setText(title);
        holder.txtDescription.setText("");
        holder.iconMenu.setImageBitmap(null);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(position,position,title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView iconMenu;
        private RelativeLayout mLayout;

        private ViewHolder(View viewItem) {
            super(viewItem);
            txtTitle = (TextView) viewItem.findViewById(R.id.txtTitle);
            txtDescription = (TextView) viewItem.findViewById(R.id.txtDescription);
            iconMenu = (ImageView) viewItem.findViewById(R.id.icon);
            mLayout = (RelativeLayout) viewItem.findViewById(R.id.relRowSetting);
        }
    }

    public void setListener(TapView listener){
        this.listener = listener;
    }
}
