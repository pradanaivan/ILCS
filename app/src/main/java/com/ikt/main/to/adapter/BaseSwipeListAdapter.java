package com.ikt.main.to.adapter;

import android.widget.BaseAdapter;

/**
 * Created by Arifin on 1/7/16.
 */
public abstract class BaseSwipeListAdapter extends BaseAdapter {
    public boolean getSwipEnableByPosition(int position){
        return true;
    }
}
