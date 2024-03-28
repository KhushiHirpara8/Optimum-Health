package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Edit_Activity extends AppCompatActivity {

    EditText edBookId,edUserN,edUserP,edDoctorName,edDoctorType,edBookDate,edBookTime;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edBookId = findViewById(R.id.ed_bookid);
        edUserN = findViewById(R.id.ed_usern);
        edUserP = findViewById(R.id.ed_userp);
        edDoctorName = findViewById(R.id.ed_doctorname);
        edDoctorType = findViewById(R.id.ed_doctortype);
        edBookDate = findViewById(R.id.ed_bookdate);
        edBookTime = findViewById(R.id.ed_booktime);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edBookId.setText(ViewPatientData.employeeArrayList.get(position).getbookId());
        edUserN.setText(ViewPatientData.employeeArrayList.get(position).getUserName());
        edUserP.setText(ViewPatientData.employeeArrayList.get(position).getUserP());
        edDoctorName.setText(ViewPatientData.employeeArrayList.get(position).getDoctorName());
        edDoctorType.setText(ViewPatientData.employeeArrayList.get(position).getDoctorType());
        edBookDate.setText(ViewPatientData.employeeArrayList.get(position).getbookDate());
        edBookTime.setText(ViewPatientData.employeeArrayList.get(position).getbookTime());

    }

    public void btn_updateData(View view) {

        final String usern = edUserN.getText().toString();
        final String userp = edUserP.getText().toString();
        final String doctortype = edDoctorType.getText().toString();
        final String doctorname = edDoctorName.getText().toString();
        final String booktime = edBookTime.getText().toString();
        final String bookdate = edBookDate.getText().toString();
        final String bookid = edBookId.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Edit_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewPatientData.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("book_id",bookid);
                params.put("user_name",usern);
                params.put("user_phone",userp);
                params.put("doctor_name",doctorname);
                params.put("doctor_type",doctortype);
                params.put("book_time",booktime);
                params.put("book_date",bookdate);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
        requestQueue.add(request);





    }
}
