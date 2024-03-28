package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailUserActivity extends AppCompatActivity {

    TextView tvbookid,tvusern,tvuserp,tvdoctortype,tvdoctorname,tvbookdate,tvbooktime;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        //Initializing Views
        tvbookid = findViewById(R.id.txtbookid);
        tvusern = findViewById(R.id.txtusern);
        tvuserp = findViewById(R.id.txtuserp);
        tvdoctortype = findViewById(R.id.txtdoctortype);
        tvdoctorname = findViewById(R.id.txtdoctorname);
        tvbookdate = findViewById(R.id.txtbookdate);
        tvbooktime = findViewById(R.id.txtbooktime);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvbookid.setText("BookID: "+BookdataActivity.employeeArrayList.get(position).getbookId());
        tvusern.setText("UserName: "+BookdataActivity.employeeArrayList.get(position).getUserName());
        tvuserp.setText("UserPhone: "+BookdataActivity.employeeArrayList.get(position).getUserP());
        tvdoctortype.setText("DoctorType: "+BookdataActivity.employeeArrayList.get(position).getDoctorType());
        tvdoctorname.setText("DoctorName: "+BookdataActivity.employeeArrayList.get(position).getDoctorName());
        tvbookdate.setText("BookDate: "+BookdataActivity.employeeArrayList.get(position).getbookDate());
        tvbooktime.setText("BookTime: "+BookdataActivity.employeeArrayList.get(position).getbookTime());




    }
}
