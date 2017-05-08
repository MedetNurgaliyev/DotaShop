package com.example.medet.android_project1.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.medet.android_project1.Adaptors.ImageAdapter;
import com.example.medet.android_project1.Adaptors.SingleViewActivity;
import com.example.medet.android_project1.R;
import com.example.medet.android_project1.cartAndCabinet.Cabinet;
import com.example.medet.android_project1.formOfLoginAndRegist.Login;
import com.example.medet.android_project1.swipeNsingleview.customSwip;

public class MainPage extends AppCompatActivity {

    ViewPager viewPager;
    com.example.medet.android_project1.swipeNsingleview.customSwip customSwip;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        customSwip = new customSwip(this);
        viewPager.setAdapter(customSwip);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(),SingleViewActivity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_cabinet:
                startActivity(new Intent(this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
