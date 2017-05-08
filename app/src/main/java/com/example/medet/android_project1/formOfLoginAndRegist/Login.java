package com.example.medet.android_project1.formOfLoginAndRegist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.medet.android_project1.R;
import com.example.medet.android_project1.cartAndCabinet.Cabinet;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity implements View.OnClickListener {


    private static final String LOGIN_URL = "http://telegrambot.kz/android/temirlan/login.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";

    private EditText unameET,passwordEt;
    TextView registerTv;
    String userName;
    String str,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg);
        unameET = (EditText) findViewById(R.id.etUnameLog);
        passwordEt = (EditText)findViewById(R.id.etPassword);
        registerTv = (TextView)findViewById(R.id.onRegistration);
        registerTv.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("Uname");
            unameET.setText(userName);
        }
    }

    private void userLogin(){
        str = unameET.getText().toString().trim();
        pass = passwordEt.getText().toString().trim();
        if(str.isEmpty() || pass.isEmpty()){
            Toast.makeText(Login.this, "There is empty fields", Toast.LENGTH_SHORT).show();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openCabinet();
                        }else {
                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }) {

            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put(KEY_USERNAME,str);
                map.put(KEY_PASS,pass);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void openCabinet(){
        Intent intent = new Intent(this, Cabinet.class);
        intent.putExtra(KEY_USERNAME,str);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnEnter:
                userLogin();
                break;
            case R.id.onRegistration:
                Intent i = new Intent(this,Registration.class);
                startActivity(i);
                break;
        }
    }
}
