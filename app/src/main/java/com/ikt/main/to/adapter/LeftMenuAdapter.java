package com.ikt.main.to.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.NotifObject;

import java.util.List;

/**
 * Created by Arifin on 2/6/16.
 */
public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[];
    private int mIcons[];

    private String name;
    private int profile;
    private String email;
    private Activity act;
    private TapView listener;
    private DBHelper dbHelper;
    private List<NotifObject> listAssign;
    private List<NotifObject> listUnassign;
    private List<NotifObject> statusUpdate31;
    private List<NotifObject> statusUpdate32;
    private List<NotifObject> statusUpdate33;
    private int countEntryTicket;
    private int countTruckAct;
    private int countAllNotif;

    public LeftMenuAdapter(Activity act,String Titles[], String Name, String Email, int Profile,TapView listener) {
        this.act = act;
        mNavTitles = Titles;
//        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
        dbHelper = new DBHelper(act);
        List<NotifObject> all = dbHelper.getAllNotif();
        countAllNotif = all.size();
        listAssign = dbHelper.getNotifByType(10);
        listUnassign = dbHelper.getNotifByType(20);
        statusUpdate31 = dbHelper.getNotifByType(31);
        statusUpdate32 = dbHelper.getNotifByType(32);
        statusUpdate33 = dbHelper.getNotifByType(33);
        countEntryTicket = listAssign.size() + listUnassign.size();
        countTruckAct = statusUpdate31.size() + statusUpdate32.size() + statusUpdate33.size();
        this.listener = listener;
    }

    @Override
    public LeftMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_drawer_layout, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final LeftMenuAdapter.ViewHolder holder, final int position) {
        if (holder.Holderid == 1) {
            final String actionName = mNavTitles[position - 1];
            holder.textView.setText(actionName);
            Drawable d;
            switch (position){
                case 2:
                    if(countEntryTicket > 0) {
                        holder.textNotif.setText(countEntryTicket + "");
                        d = act.getResources().getDrawable(R.drawable.bg_notif_red);
                        holder.textNotif.setBackgroundDrawable(d);
                    }else{
                        holder.textNotif.setVisibility(View.GONE);
                    }
                    holder.textNotif.setVisibility(View.GONE);
                    break;
                case 3:
                    if(countTruckAct > 0) {
                        holder.textNotif.setText(countTruckAct + "");
                        d = act.getResources().getDrawable(R.drawable.bg_notif_green);
                        holder.textNotif.setBackgroundDrawable(d);
                    }else{
                        holder.textNotif.setVisibility(View.GONE);
                    }
                    holder.textNotif.setVisibility(View.GONE);
                    break;
                case 4:
                    if(countAllNotif > 0) {
                        holder.textNotif.setText(countAllNotif + "");
                        d = act.getResources().getDrawable(R.drawable.bg_notif_cyan);
                        holder.textNotif.setBackgroundDrawable(d);
                    }else{
                        holder.textNotif.setVisibility(View.GONE);
                    }
                    break;
                default:
                    holder.textNotif.setVisibility(View.GONE);
                    break;
            }

            holder.btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setAction(holder.Holderid, position, actionName);
                }

            });

//            holder.imageView.setImageResource(mIcons[position - 1]);
        } else {
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        TextView textNotif;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        RelativeLayout btnMenu;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                textNotif = (TextView) itemView.findViewById(R.id.rowNotif);
                btnMenu = (RelativeLayout) itemView.findViewById(R.id.top_layout);
//                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.photo_profile);
                Holderid = 0;
            }
        }
    }
}
