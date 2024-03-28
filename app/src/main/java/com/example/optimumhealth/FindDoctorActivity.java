package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindDoctorActivity extends AppCompatActivity {

    CardView familyphysician,allergists,dentist,surgeon,cardiologists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        familyphysician = findViewById(R.id.cardFDFamilyPhysician);
        familyphysician.setOnClickListener(view -> {
            Intent it2 = getIntent();
            String username = it2.getStringExtra("username");
            String phone = it2.getStringExtra("phone");
            Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","Family Physicians");
            it.putExtra("username",username);
            it.putExtra("phone",phone);
            startActivity(it);
            finish();
        });

        allergists = findViewById(R.id.cardFDAllergists);
        allergists.setOnClickListener(view -> {
            Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","Allergists");
            Intent it2 = getIntent();
            String username = it2.getStringExtra("username");
            String phone = it2.getStringExtra("phone");
            it.putExtra("username",username);
            it.putExtra("phone",phone);
            startActivity(it);
            finish();
        });

        dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(view -> {
            Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","Dentist");
            Intent it2 = getIntent();
            String username = it2.getStringExtra("username");
            String phone = it2.getStringExtra("phone");
            it.putExtra("username",username);
            it.putExtra("phone",phone);
            startActivity(it);
            finish();
        });

        surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(view -> {
            Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","Surgeon");
            Intent it2 = getIntent();
            String username = it2.getStringExtra("username");
            String phone = it2.getStringExtra("phone");
            it.putExtra("username",username);
            it.putExtra("phone",phone);
            startActivity(it);
            finish();
        });

        cardiologists = findViewById(R.id.cardFDCardiologists);
        cardiologists.setOnClickListener(view -> {
            Intent it = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
            it.putExtra("title","Cardiologists");
            Intent it2 = getIntent();
            String username = it2.getStringExtra("username");
            String phone = it2.getStringExtra("phone");
            it.putExtra("username",username);
            it.putExtra("phone",phone);
            startActivity(it);
            finish();
        });

    }
}
