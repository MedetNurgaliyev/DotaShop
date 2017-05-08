package com.example.medet.android_project1.formOfLoginAndRegist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.medet.android_project1.R;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener {


//    There is link to our server below
    private static final String REGISTER_URL = "http://telegrambot.kz/android/temirlan/register.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASS = "password";
    public static final String KEY_EMAIL = "email";

    private EditText emailEt, passwordEt, passwordEt2, unameET;
    private Button registrate;
    private TextView enterTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_reg);

        emailEt = (EditText) findViewById(R.id.etEmailReg);
        passwordEt = (EditText) findViewById(R.id.etPassReg);
        passwordEt2 = (EditText) findViewById(R.id.etPassReg2);
        unameET = (EditText) findViewById(R.id.etUnameReg);
        registrate = (Button) findViewById(R.id.btnReg);
        enterTv = (TextView) findViewById(R.id.onLogin);
        enterTv.setOnClickListener(this);
    }

    private void registerUser() {
        Intent intent = new Intent(this,Login.class);

        final String email = emailEt.getText().toString().trim();
        final String uname = unameET.getText().toString().trim();
        final String pass1 = passwordEt.getText().toString().trim();
        final String pass2 = passwordEt2.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(Registration.this, "Email field is empty!", Toast.LENGTH_SHORT).show();
        }
        if(uname.isEmpty()){
            Toast.makeText(Registration.this, "Username field is empty!", Toast.LENGTH_SHORT).show();
        }
        if(!pass1.equals(pass2)||(pass1.isEmpty() || pass2.isEmpty())) {
            Toast.makeText(Registration.this, "Password don't match!", Toast.LENGTH_SHORT).show();
        }
        else{

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Registration.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,uname);
                params.put(KEY_PASS,pass1);
                params.put(KEY_EMAIL,email);
                return params;
            }
        };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            intent.putExtra("Uname",uname);
            startActivity(intent);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReg:
            registerUser();
                break;
            case R.id.onLogin:
                startActivity(new Intent(this,Login.class));
           break;
        }
    }
}
