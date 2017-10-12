package com.ikt.main.to.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ikt.main.to.fragment.AssignTicketFragment;
import com.ikt.main.to.fragment.UnassignTicketFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT;
    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
        PAGE_COUNT = titles2.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fr = null;
        switch (position) {
            case 0:
                fr = new AssignTicketFragment();
                break;
            case 1:
                fr = new UnassignTicketFragment();
                break;
        }
        return fr;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}