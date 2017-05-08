package com.example.medet.android_project1.Adaptors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medet.android_project1.Adaptors.ImageAdapter;
import com.example.medet.android_project1.R;

public class SingleViewActivity extends AppCompatActivity{
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);
        tv=(TextView)findViewById(R.id.tv);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);

        tv.setText("This is number "+position+" image ");
    }
}
