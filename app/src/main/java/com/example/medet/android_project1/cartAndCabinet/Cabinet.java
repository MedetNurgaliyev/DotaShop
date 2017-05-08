package com.example.medet.android_project1.cartAndCabinet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medet.android_project1.R;

import java.io.IOException;
import java.util.Random;

public class Cabinet extends AppCompatActivity {

    ImageView image;
    final int CAMERA_CAPTURE = 1;
    private static final int REQUEST = 2;
    String userName;
    TextView unameET,rak;
    int q;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cabinet_layout);
        image = (ImageView) findViewById(R.id.imageView2);
        unameET = (TextView)findViewById(R.id.tv1);
        rak = (TextView)findViewById(R.id.tv2);
        registerForContextMenu(image);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("username");
            unameET.setText(userName);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.imageView2) {
            menu.add(0, 1, 0, "Take a picture");
            menu.add(0, 2, 0, "Choose from gallery");
        } else Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                try {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, CAMERA_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    String errorMessage = "Ваше устройство не поддерживает съемку";
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                try{
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);}
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap img = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            }
        }
        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                img = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(img);
        }
        Random r = new Random();
        q = r.nextInt(1);
        if(q==0){
            rak.setText("Извините,но вы РАК !");
        }
        if(q==1){
            rak.setText("Ваще лицо не похоже на лицо РАКА)");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}