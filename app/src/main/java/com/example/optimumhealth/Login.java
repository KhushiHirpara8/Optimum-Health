package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.Connection;
import java.sql.DriverManager;

public class Login extends AppCompatActivity {

    TextView textViewsignUpText,AdminLink,NurseLink;
    TextInputEditText textInputEditTextusername, textInputEditTextpassword;
    Button buttonLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AdminLink = findViewById(R.id.adminlink);
        NurseLink = findViewById(R.id.nurselink);
        textInputEditTextusername = findViewById(R.id.username);
        textInputEditTextpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.Login);
        textViewsignUpText = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewsignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });

        NurseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NurseLogin.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;
                username = String.valueOf(textInputEditTextusername.getText());
                password = String.valueOf(textInputEditTextpassword.getText());

                if (!username.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = username.trim();
                            data[1] = password.trim();


                            PutData putData  = new PutData("https://optimumhealth.000webhostapp.com/login.php","POST",field,data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.putExtra("username",username);
                                        String phone="6354542757";
                                        intent.putExtra("phone",phone);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid username and password.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}