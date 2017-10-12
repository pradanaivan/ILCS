package com.ikt.main.to.adapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ikt.main.to.object.MainMenuObject;
import com.ikt.main.to.R;
import com.ikt.main.to.controller.TapGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arifin on 14/12/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {


    private List<MainMenuObject> mItems;
    private Activity act;
    private TapGrid onTapListener;

    public GridAdapter(Activity act) {
        super();
        this.act = act;

        String[] titles = act.getResources().getStringArray(R.array.title_main_menu);
        TypedArray images = act.getResources().obtainTypedArray(R.array.icon_main_menu);

        mItems = new ArrayList<MainMenuObject>();
        for (int i = 0; i < titles.length-1; i++) {
            int resourceId = images.getResourceId(i, 0);
            MainMenuObject menuObject = new MainMenuObject();
            menuObject.setName(titles[i]);
            menuObject.setThumbnail(resourceId);
            mItems.add(menuObject);
        }
        images.recycle();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        MainMenuObject nature = mItems.get(i);
        viewHolder.txt_title.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTapListener != null) {
                    onTapListener.onTapItemGrid(i);
                }
            }
        });
//        viewHolder.btnRipple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//
//            @Override
//            public void onComplete(RippleView rippleView) {
//                if (onTapListener != null) {
//                    onTapListener.onTapItemGrid(i);
//                }
//            }
//
//        });
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView txt_title;
        public RelativeLayout topLayout;
//        public RippleView btnRipple;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            topLayout = (RelativeLayout) itemView.findViewById(R.id.top_layout);
//            btnRipple = (RippleView) itemView.findViewById(R.id.more);
        }
    }

    public void setOnTapListener(TapGrid onTapListener)
    {
        this.onTapListener = onTapListener;
    }

}
