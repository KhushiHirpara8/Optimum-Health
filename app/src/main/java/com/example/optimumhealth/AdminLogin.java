package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import android.os.Bundle;

public class AdminLogin extends AppCompatActivity {

    TextView textViewsignUpText,AdminLink;
    TextInputEditText textInputEditTextusername, textInputEditTextpassword;
    Button buttonLogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        textInputEditTextusername = findViewById(R.id.username);
        textInputEditTextpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.Login);
        textViewsignUpText = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewsignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
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

                if (username.equals("Admin@123") && password.equals("123123")) {
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong ID or PASSWORD", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}
