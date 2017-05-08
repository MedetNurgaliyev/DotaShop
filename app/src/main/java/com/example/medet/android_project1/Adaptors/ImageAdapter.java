package com.example.medet.android_project1.Adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.medet.android_project1.R;

public class ImageAdapter extends BaseAdapter{
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(30, 50, 30, 50);
//            imageView.setPadding
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    Integer[] mThumbIds = {
            R.mipmap.dota1, R.mipmap.dota2,
            R.mipmap.dota3, R.mipmap.dota4,
            R.mipmap.dota5, R.mipmap.dota6,
            R.mipmap.dota7, R.mipmap.dota8,
            R.mipmap.dota9, R.mipmap.dota10,
            R.mipmap.dota11, R.mipmap.dota12
    };

}