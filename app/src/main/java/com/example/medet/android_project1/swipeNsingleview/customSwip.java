package com.example.medet.android_project1.swipeNsingleview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.medet.android_project1.R;


public class customSwip extends PagerAdapter {
    private int[] imageResources = {R.drawable.capture3,R.drawable.capture2,R.drawable.capture1};
    private LayoutInflater layoutInflater;
    private Context ctx;



    public customSwip(Context c) {
        ctx = c;
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.activity_custom_swip,container,false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.swipImageView);
        imageView.setImageResource(imageResources[position]);
        container.addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }



}
