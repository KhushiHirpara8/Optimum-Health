package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_Data_Doctor_Activity extends AppCompatActivity {

    EditText txtDr_Name,txtDr_Type,txtDr_Exp,txtDr_Mob,txtDr_Fees;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_doctor);

        String[] languages = getResources().getStringArray(R.array.doctor_type);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        // get reference to the autocomplete text view
        AutoCompleteTextView autocompleteTV = findViewById(R.id.edtDrType);
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter);

        autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the selected item from the adapter
                String selectedItem = parent.getItemAtPosition(position).toString();
                // display the selected item
                Toast.makeText(Add_Data_Doctor_Activity.this, "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });


        txtDr_Name = findViewById(R.id.edtDrName);
        txtDr_Type = autocompleteTV;//findViewById(R.id.edtDrType);
        txtDr_Exp = findViewById(R.id.edtDrExp);
        txtDr_Mob = findViewById(R.id.edtDrMob);
        txtDr_Fees = findViewById(R.id.edtDrFees);
        btn_insert = findViewById(R.id.btnInsert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String drname = txtDr_Name.getText().toString().trim();
        final String drtype = txtDr_Type.getText().toString().trim();
        final String drexp = txtDr_Exp.getText().toString().trim();
        final String drmob = txtDr_Mob.getText().toString().trim();
        final String drfees = txtDr_Fees.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(drname.isEmpty()){
            Toast.makeText(this, "Enter Doctor Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(drtype.isEmpty()){
            Toast.makeText(this, "Enter Doctor Type", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(drexp.isEmpty()){
            Toast.makeText(this, "Enter doctor experience", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(drmob.isEmpty()){
            Toast.makeText(this, "Enter doctor mobile no.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(drfees.isEmpty()) {
            Toast.makeText(this, "Enter doctor fees", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/insertdoctor.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(Add_Data_Doctor_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ViewDoctorData.class));
                                progressDialog.dismiss();
                                finish();
                            }
                            else{
                                Toast.makeText(Add_Data_Doctor_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Data_Doctor_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("dr_name",drname);
                    params.put("dr_type",drtype);
                    params.put("dr_exp",drexp);
                    params.put("dr_mob",drmob);
                    params.put("dr_fees",drfees);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Doctor_Activity.this);
            requestQueue.add(request);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
