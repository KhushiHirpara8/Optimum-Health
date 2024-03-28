package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class Add_Data_Nurse_Activity extends AppCompatActivity {

    EditText txtNurseN,txtNurseP;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_nurse);

        txtNurseN = findViewById(R.id.edtNurseN);
        txtNurseP = findViewById(R.id.edtNurseP);
        btn_insert = findViewById(R.id.btnInsert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String nursename = txtNurseN.getText().toString().trim();
        final String nursepass = txtNurseP.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(nursename.isEmpty()){
            Toast.makeText(this, "Enter Nurse Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(nursepass.isEmpty()) {
            Toast.makeText(this, "Enter Nurse Pass",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/insertnurse.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(Add_Data_Nurse_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                finish();
                            }
                            else{
                                Toast.makeText(Add_Data_Nurse_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Data_Nurse_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("nursen",nursename);
                    params.put("nursep",nursepass);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Nurse_Activity.this);
            requestQueue.add(request);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
