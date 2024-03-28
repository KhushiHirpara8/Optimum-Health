package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {
    Button btn,viewall,viewnall,viewdall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btn = findViewById(R.id.buttonAHBack);
        viewall = findViewById(R.id.view_all);
        viewnall = findViewById(R.id.viewn_all);
        viewdall = findViewById(R.id.viewd_all);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AdminHome.this,AdminLogin.class);
                startActivity (it);
                finish();
            }
        });

        viewall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(AdminHome.this,ViewPatientData.class);
                startActivity (it2);
            }
        });

        viewnall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(AdminHome.this,ViewNurseData.class);
                startActivity (it3);
            }
        });

        viewdall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(AdminHome.this,ViewDoctorData.class);
                startActivity (it3);
            }
        });
    }
}