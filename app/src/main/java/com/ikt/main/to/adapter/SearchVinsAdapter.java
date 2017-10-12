package com.ikt.main.to.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.object.TruckObject;
import com.ikt.main.to.object.VInObjectList;

import java.util.ArrayList;

/**
 * Created by arifins on 10/5/16.
 */

public class SearchVinsAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<VInObjectList> obj;
    private ArrayList<VInObjectList> objSelected;
    private TapView tapView;
    private TapView listener;
    private LayoutInflater inflater = null;

    public SearchVinsAdapter(Activity activity, ArrayList<VInObjectList> obj, TapView listener) {
        this.activity = activity;
        this.obj = obj;
        this.listener = listener;
        objSelected = new ArrayList<VInObjectList>();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public Object getItem(int i) {
        return obj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View vi = convertView;
        Holder holder;
        // The child views in each row.
        CheckBox checkBox1 ;
        TextView txtTitle1 ;
        VInObjectList vInObjectList = (VInObjectList) getItem(i);

        if (convertView == null) {

            vi = inflater.inflate(R.layout.row_vins_layout, null);
            holder = new Holder();
            holder.txtTitle = (TextView) vi.findViewById(R.id.txt_title);
            holder.checkBox = (CheckBox) vi.findViewById(R.id.checkbox);
            holder.relVins = (RelativeLayout) vi.findViewById(R.id.relRowVins);
            checkBox1 = holder.checkBox;
            txtTitle1 = holder.txtTitle;
            vi.setTag(holder);

            // If CheckBox is toggled, update the planet it is tagged with.
            checkBox1.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    VInObjectList vInObjectList1 = (VInObjectList) cb.getTag();
                    vInObjectList1.setChecked( cb.isChecked() );
                    if(cb.isChecked()){
                        if(!objSelected.contains(vInObjectList1))
                            objSelected.add(vInObjectList1);

                    }else{
                        if(objSelected.contains(vInObjectList1))
                            objSelected.remove(vInObjectList1);
                    }
                }
            });
        } else {
            holder = (Holder) vi.getTag();
            checkBox1 = holder.checkBox ;
            txtTitle1 = holder.txtTitle ;
        }


        checkBox1.setTag( vInObjectList );
        checkBox1.setChecked( vInObjectList.isChecked() );
        txtTitle1.setText( vInObjectList.getName() );

        return vi;
    }

    public class Holder {
        TextView txtTitle;
        CheckBox checkBox;
        RelativeLayout relVins;
    }

    public ArrayList<VInObjectList> getObjSelected() {
        return objSelected;
    }
}
