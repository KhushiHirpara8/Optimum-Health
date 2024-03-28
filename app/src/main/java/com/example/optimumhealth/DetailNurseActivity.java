package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailNurseActivity extends AppCompatActivity {

    TextView tvnurseid,tvnursen;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nurse);

        //Initializing Views
        tvnurseid = findViewById(R.id.txtnurseid);
        tvnursen = findViewById(R.id.txtnursen);


        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvnurseid.setText("NurseID: "+ViewNurseData.employeeArrayList.get(position).getnurseId());
        tvnursen.setText("NurseName: "+ViewNurseData.employeeArrayList.get(position).getNurseName());



    }
}