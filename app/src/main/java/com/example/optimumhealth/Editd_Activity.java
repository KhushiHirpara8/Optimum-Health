package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
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

public class Editd_Activity extends AppCompatActivity {

    TextView edDrTypeold;
    EditText edDrId,edDrName,edDrType,edDrExp,edDrMob,edDrFees;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editd);

        String[] languages = getResources().getStringArray(R.array.doctor_etype);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_eitem, languages);
        // get reference to the autocomplete text view
        AutoCompleteTextView autocompleteTV = findViewById(R.id.ed_booktype);
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter);

        autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the selected item from the adapter
                String selectedItem = parent.getItemAtPosition(position).toString();
                // display the selected item
                Toast.makeText(Editd_Activity.this, "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        edDrId = findViewById(R.id.ed_drid);
        edDrName = findViewById(R.id.ed_drname);
        edDrType = findViewById(R.id.ed_booktype);
        edDrExp = findViewById(R.id.ed_drexp);
        edDrMob = findViewById(R.id.ed_drmob);
        edDrFees = findViewById(R.id.ed_drfees);
        edDrTypeold =findViewById(R.id.ed_drtypeold);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edDrId.setText(ViewDoctorData.employeeArrayList.get(position).getDrId());
        edDrTypeold.setText(ViewDoctorData.employeeArrayList.get(position).getDrType());
        edDrName.setText(ViewDoctorData.employeeArrayList.get(position).getDrName());
        edDrExp.setText(ViewDoctorData.employeeArrayList.get(position).getDrExp());
        edDrMob.setText(ViewDoctorData.employeeArrayList.get(position).getDrMob());
        edDrFees.setText(ViewDoctorData.employeeArrayList.get(position).getDrFees());

    }

    public void btn_updateData(View view) {

        final String drname = edDrName.getText().toString();
        final String drtype = edDrType.getText().toString();
        final String drexp = edDrExp.getText().toString();
        final String drmob = edDrMob.getText().toString();
        final String drfees = edDrFees.getText().toString();
        final String drid = edDrId.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/updatedoctor.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Editd_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewDoctorData.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Editd_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put("dr_id",drid);
                params.put("dr_type",drtype);
                params.put("dr_name",drname);
                params.put("dr_exp",drexp);
                params.put("dr_mob",drmob);
                params.put("dr_fees",drfees);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Editd_Activity.this);
        requestQueue.add(request);

    }
}