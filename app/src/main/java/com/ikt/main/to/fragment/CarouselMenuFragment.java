package com.ikt.main.to.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikt.main.to.R;
import com.ikt.main.to.activities.HomeActivity;
import com.ikt.main.to.adapter.CarouselMenuAdapter;
import com.ikt.main.to.component.Carousel;

/**
 * Created by Arifin on 4/30/16.
 */
public class CarouselMenuFragment extends Fragment {

    private TypedArray images;
    private int[] icons;

    public CarouselMenuFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.carousel_layout, container, false);
        Carousel carousel = (Carousel) v.findViewById(R.id.carousel);
        carousel.setOnItemClickListener(new CarouselMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarouselMenuAdapter<?> parent, View view, int position, long id) {
                Fragment fr = null;
                switch (position){
                    case 0:
                        fr = new AnnouncementFragment();
                        ((HomeActivity) getActivity()).replaceFragment(fr);
                        break;
                    case 1:
                        fr = new EntryTicketFragment();
                        ((HomeActivity) getActivity()).replaceFragment(fr);
                        break;
                    case 2:
                        fr = new TruckAcitivitiesFragment();
                        ((HomeActivity) getActivity()).replaceFragment(fr);
                        break;
                    case 4:
                        fr = new DriverFragment();
                        ((HomeActivity) getActivity()).replaceFragment(fr);
                        break;
//            case 7:
//                fr = new SettingFragment();
//                ((HomeActivity) getActivity()).replaceFragment(fr);
                }
            }
        });
        return v;
    }
}
