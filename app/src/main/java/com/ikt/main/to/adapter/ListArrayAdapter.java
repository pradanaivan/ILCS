package com.ikt.main.to.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ikt.main.to.R;

public class ListArrayAdapter extends ArrayAdapter<String> {

    private String mHint = "--- Select ---";
    private List<String> objects;

    public ListArrayAdapter(Context context, List<String> objects, List<String> objects_id) {
        super(context, R.layout.layout_row_dropdown, R.id.text, objects_id);
        this.objects = objects;
    }

    public void setHint(String mHint) {
        this.mHint = mHint;
    }

    public String getHint() {
        return mHint;
    }

    @Override
    public String getItem(int position) {
        if (position == super.getCount() || 0 == super.getCount()) {
            return (getHint());
        }
        return super.getItem(position);
    }
    @Override
    public int getPosition(String item) {
    	return super.getPosition(item);
    }
    
    @Override
    public long getItemId(int position) {
    	return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView text = (TextView) v.findViewById(R.id.text);
        if (position == super.getCount()) {
            text.setVisibility(View.GONE);
            TextView label2 = (TextView) v.findViewById(R.id.label);
            label2.setVisibility(View.VISIBLE);
            if (position == super.getCount() || 0 == super.getCount()) {
//                return (getHint());
            	label2.setHint(getHint());
            }
            
        } else {
            text.setVisibility(View.VISIBLE);
            text.setText(objects.get(position));
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView text = (TextView) v.findViewById(R.id.text);
        if (super.getCount() != 0) {
            if (position != super.getCount()) {
                text.setVisibility(View.VISIBLE);
                text.setText(objects.get(position));
            } else {
                text.setText("");
                text.setVisibility(View.GONE);
            }
        }
        return v;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    public static boolean isNotSelected(Spinner view) {
        if (null == view || null == view.getAdapter() || view.getSelectedItemPosition() + 1 == view.getAdapter().getCount()) {
            return true;
        }
        return false;
    }

    public static void selectHint(Spinner view) {
        if (null != view) {
            view.setSelection(view.getCount() - 1);
        }
    }

}
