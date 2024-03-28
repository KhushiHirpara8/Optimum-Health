package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

        public class DetailDoctorActivity extends AppCompatActivity {

            TextView tvdrid,tvdrname,tvdrtype,tvdrexp,tvdrmob,tvdrfees;

            int position;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_detail_doctor);


                //Initializing Views
                tvdrid = findViewById(R.id.txtdrid);
                tvdrname = findViewById(R.id.txtdrname);
                tvdrtype = findViewById(R.id.txtdrtype);
                tvdrexp = findViewById(R.id.txtdrexp);
                tvdrmob = findViewById(R.id.txtdrmob);
                tvdrfees = findViewById(R.id.txtdrfees);



                Intent intent =getIntent();
                position = intent.getExtras().getInt("position");

                tvdrid.setText("DrID: "+ViewDoctorData.employeeArrayList.get(position).getDrId());
                tvdrname.setText("DrName: "+ViewDoctorData.employeeArrayList.get(position).getDrName());
                tvdrtype.setText("DrType: "+ViewDoctorData.employeeArrayList.get(position).getDrType());
                tvdrexp.setText("DrExp: "+ViewDoctorData.employeeArrayList.get(position).getDrExp());
                tvdrmob.setText("DrMob: "+ViewDoctorData.employeeArrayList.get(position).getDrMob());
                tvdrfees.setText("DrFees: "+ViewDoctorData.employeeArrayList.get(position).getDrFees());



            }
        }

