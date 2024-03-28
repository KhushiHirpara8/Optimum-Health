package com.example.optimumhealth;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewDoctorData extends AppCompatActivity {

            ListView listView;
            MydAdapter adapter;
            public static ArrayList<Employeed> employeeArrayList = new ArrayList<>();
            String url = "https://optimumhealth.000webhostapp.com/retrievedoctor.php";
            Employeed employee;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_view_doctor_data);

                listView = findViewById(R.id.mydListView);
                adapter = new MydAdapter(this,employeeArrayList);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                        CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                        builder.setTitle(employeeArrayList.get(position).getDrName());
                        builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                switch (i){

                                    case 0:
                                        startActivity(new Intent(getApplicationContext(),DetailDoctorActivity.class)
                                                .putExtra("position",position));

                                        break;

                                    case 1:
                                        startActivity(new Intent(getApplicationContext(),Editd_Activity.class)
                                                .putExtra("position",position));

                                        break;

                                    case 2:
                                        deleteData(employeeArrayList.get(position).getDrId());

                                        break;
                                }

                            }
                        });


                        builder.create().show();

                    }
                });

                retrieveData();


            }

            private void deleteData(final String id) {

                StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/deletedoctor.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equalsIgnoreCase("Data Deleted")){
                                    Toast.makeText(com.example.optimumhealth.ViewDoctorData.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(com.example.optimumhealth.ViewDoctorData.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(com.example.optimumhealth.ViewDoctorData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("drid", id);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(request);


            }

            public void retrieveData(){

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                employeeArrayList.clear();
                                try{

                                    JSONObject jsonObject = new JSONObject(response);
                                    String sucess = jsonObject.getString("success");
                                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                                    if(sucess.equals("1")){


                                        for(int i=0;i<jsonArray.length();i++){

                                            JSONObject object = jsonArray.getJSONObject(i);

                                            String drid = object.getString("dr_id");
                                            String drname = object.getString("dr_name");
                                            String drtype = object.getString("dr_type");
                                            String drexp = object.getString("dr_exp");
                                            String drmob = object.getString("dr_mob");
                                            String drfees = object.getString("dr_fees");

                                            employee = new Employeed(drid,drtype,drname,drexp,drmob,drfees);
                                            employeeArrayList.add(employee);
                                            adapter.notifyDataSetChanged();

                                        }

                                    }

                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(com.example.optimumhealth.ViewDoctorData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(request);


            }
            public void btn_add_activity(View view) {
                startActivity(new Intent(getApplicationContext(),Add_Data_Doctor_Activity.class));
            }
        }