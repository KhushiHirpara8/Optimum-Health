package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_Data_Activity extends AppCompatActivity {

    EditText txtUserN,txtUserP,txtDoctorType,txtDoctorName,txtBookTime,txtBookDate;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        txtUserN = findViewById(R.id.edtUserN);
        txtUserP = findViewById(R.id.edtUserP);
        txtDoctorType = findViewById(R.id.edtDoctorType);
        txtDoctorName = findViewById(R.id.edtDoctorName);
        txtBookTime = findViewById(R.id.edtBookTime);
        txtBookDate = findViewById(R.id.edtBookDate);
        btn_insert = findViewById(R.id.btnInsert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String username = txtUserN.getText().toString().trim();
        final String userphone = txtUserP.getText().toString().trim();
        final String doctortype = txtDoctorName.getText().toString().trim();
        final String doctorname = txtDoctorType.getText().toString().trim();
        final String bookdate = txtBookDate.getText().toString().trim();
        final String booktime = txtBookTime.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(username.isEmpty()){
            Toast.makeText(this, "Enter User Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(userphone.isEmpty()){
            Toast.makeText(this, "Enter User Phone", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(doctortype.isEmpty()){
            Toast.makeText(this, "Enter doctor type", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(doctorname.isEmpty()){
            Toast.makeText(this, "Enter doctor name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(bookdate.isEmpty()){
            Toast.makeText(this, "Enter book date", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(booktime.isEmpty()){
            Toast.makeText(this, "Enter book time", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(Add_Data_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ViewPatientData.class));
                                progressDialog.dismiss();
                                finish();
                            }
                            else{
                                Toast.makeText(Add_Data_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Data_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("usern",username);
                    params.put("userp",userphone);
                    params.put("doctor_type",doctortype);
                    params.put("doctor_name",doctorname);
                    params.put("book_time",booktime);
                    params.put("book_date",bookdate);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Activity.this);
            requestQueue.add(request);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
