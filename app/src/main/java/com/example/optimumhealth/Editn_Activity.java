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

public class Editn_Activity extends AppCompatActivity {

    EditText edNurseId,edNurseN;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editn);

        edNurseId = findViewById(R.id.ed_nurseid);
        edNurseN = findViewById(R.id.ed_nursen);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edNurseId.setText(ViewNurseData.employeeArrayList.get(position).getnurseId());
        edNurseN.setText(ViewNurseData.employeeArrayList.get(position).getNurseName());

    }

    public void btn_updateData(View view) {
        final String nurseid = edNurseId.getText().toString();
        final String nursen = edNurseN.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/updatenurse.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Editn_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewNurseData.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Editn_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("nurse_id",nurseid);
                params.put("nurse_name",nursen);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Editn_Activity.this);
        requestQueue.add(request);





    }
}
