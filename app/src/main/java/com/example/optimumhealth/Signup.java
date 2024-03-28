package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;
import com.google.android.material.textfield.TextInputEditText;
import android.os.Handler;
import android.os.Looper;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class Signup extends AppCompatActivity {

    TextInputEditText textInputEditTextusername, textInputEditTextemail, textInputEditTextphone, textInputEditTextpassword;
    Button buttonSignup;
    TextView textViewloginText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputEditTextusername=findViewById(R.id.username);
        textInputEditTextemail=findViewById(R.id.email);
        textInputEditTextphone=findViewById(R.id.phone);
        textInputEditTextpassword=findViewById(R.id.password);
        buttonSignup=findViewById(R.id.Signup);
        textViewloginText=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);

        textViewloginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username,email,phone,password;
                username=String.valueOf(textInputEditTextusername.getText());
                email=String.valueOf(textInputEditTextemail.getText());
                phone=String.valueOf(textInputEditTextphone.getText());
                password=String.valueOf(textInputEditTextpassword.getText());

                if(!username.equals("") && !email.equals("") && phone.length()==10 && Patterns.EMAIL_ADDRESS.matcher(email).matches() && !phone.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[4];
                            field[0] = "username";
                            field[1] = "phone";
                            field[2] = "email";
                            field[3] = "password";
                            String[] data = new String[4];
                            data[0] = username.trim();
                            data[1] = phone.trim();
                            data[2] = email.trim();
                            data[3] = password.trim();


                            PutData putData = new PutData("https://optimumhealth.000webhostapp.com/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "User Already exist...", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(), "username, phone, email must be unique...", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Error while sending your data.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required in a right format", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}