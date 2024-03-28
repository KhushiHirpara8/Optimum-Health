package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NurseHome extends AppCompatActivity {
    Button btn,view_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_home);
        btn = findViewById(R.id.buttonAHBack);
        view_all = findViewById(R.id.view_all);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(NurseHome.this,NurseLogin.class);
                startActivity (it2);
                finish();
            }
        });
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NurseHome.this,ViewPatientData.class);
                startActivity(it);
            }
        });

    }
}