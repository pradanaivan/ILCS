package com.ikt.main.to.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.util.Config;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Arifin on 5/1/16.
 */
public class NotificationAdapter extends BaseAdapter {

    private Activity a;
    private ArrayList<HashMap<String, String>> d;
    private static LayoutInflater inflater=null;
    private TapView listener;

    public NotificationAdapter(Activity a, ArrayList<HashMap<String, String>> d, TapView listener) {
        this.a = a;
        this.d = d;
        this.listener = listener;
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return d.size();
    }

    @Override
    public Object getItem(int id) {
        return id;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            if(convertView==null)
                vi = inflater.inflate(R.layout.row_notif, null);

            holder = new ViewHolder();

            holder.txtTitle = (TextView)vi.findViewById(R.id.txtTitle); // title
	        holder.img = (ImageView)vi.findViewById(R.id.ic_notif); // thumb image
            holder.txtBy = (TextView) vi.findViewById(R.id.txtBy);
            holder.txtMsg = (TextView) vi.findViewById(R.id.txtMsg);
            holder.txtMsgAdd = (TextView) vi.findViewById(R.id.txtMsgAdd);
            holder.viewRow = (LinearLayout) vi.findViewById(R.id.list_item);
            vi.setTag(holder);

        }else{
            holder = (ViewHolder) vi.getTag();
        }

        HashMap<String, String> song = d.get(position);
        String msg = song.get(Config.KEY_NAME);
        final String[] messages = msg.split("#");
        int code = Integer.parseInt(messages[0]);
        String title = "";
        String[] tglJam = null;
        switch (code){
            case 10:
                title = "Entry Ticket : "+ messages[1];
                holder.txtTitle.setText(title);
                holder.txtBy.setText("Via Mobile By "+messages[3]);
                holder.txtMsg.setText(messages[2]);
                holder.txtMsgAdd.setVisibility(View.GONE);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_entry_ticket));
                break;
            case 20:
                title = "Unassigned Ticket : "+messages[1];
                holder.txtTitle.setText(title);
                holder.txtBy.setText("Via Web Portal ");
                holder.txtMsg.setText(messages[2]);
                holder.txtMsgAdd.setVisibility(View.GONE);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_unassign_ticket));
                break;
            case 31:
                title = "Status Truck Update ";
                holder.txtTitle.setText(title);
                tglJam = messages[3].split(" ");
                holder.txtBy.setText("at "+tglJam[1]+","+tglJam[0]);
                holder.txtMsg.setText(messages[1]+","+messages[2]);
                holder.txtMsgAdd.setText(messages[4]);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_status_update));
                break;
            case 32:
                title = "Status Truck Update ";
                holder.txtTitle.setText(title);
                tglJam = messages[3].split(" ");
                holder.txtBy.setText("at "+tglJam[1]+","+tglJam[0]);
                holder.txtMsg.setText(messages[1]+","+messages[2]);
                holder.txtMsgAdd.setText(messages[4]);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_status_update));
                break;
            case 33:
                title = "Status Truck Update ";
                holder.txtTitle.setText(title);
                tglJam = messages[3].split(" ");
                holder.txtBy.setText("at "+tglJam[1]+","+tglJam[0]);
                holder.txtMsg.setText(messages[1]+","+messages[2]);
                holder.txtMsgAdd.setText(messages[4]);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_status_update));
                break;
            case 40:
                title = messages[1] +" : pengumuman";
                holder.txtTitle.setText(title);
                holder.txtBy.setVisibility(View.GONE);
                holder.txtMsg.setText(messages[2]);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_info));
                holder.viewRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(0, position, messages[1]);
                    }
                });
                break;
            case 50:
                title = "Broadcast IKT";
                holder.txtTitle.setText(title);
                holder.txtBy.setVisibility(View.GONE);
                holder.txtMsgAdd.setVisibility(View.GONE);
                holder.txtMsg.setText(messages[2]);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_info));
                holder.viewRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setAction(0, position, messages[2]);
                    }
                });
                break;
            case 90:
                title = "Error";
                holder.txtTitle.setText(title);
                holder.txtBy.setVisibility(View.GONE);
                holder.txtMsg.setText(messages[2]);
                holder.txtMsgAdd.setVisibility(View.GONE);
                holder.img.setImageDrawable(ContextCompat.getDrawable(a, R.drawable.notif_error));
                break;
        }
        holder.viewRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.setAction(1, position, messages[2]);
                return true;
            }
        });
        holder.viewRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setAction(0, position, messages[2]);
                return;
            }
        });
        return vi;
    }

    private class ViewHolder{
        TextView txtTitle;
        TextView txtMsg;
        TextView txtMsgAdd;
        TextView txtBy;
		ImageView img;
        LinearLayout viewRow;
    }
}
