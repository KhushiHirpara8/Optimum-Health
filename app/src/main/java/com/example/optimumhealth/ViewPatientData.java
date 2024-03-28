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

public class ViewPatientData extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    String url = "https://optimumhealth.000webhostapp.com/retrieve.php";
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_data);

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,employeeArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(employeeArrayList.get(position).getUserName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                                        .putExtra("position",position));

                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),Edit_Activity.class)
                                        .putExtra("position",position));

                                break;

                            case 2:

                                deleteData(employeeArrayList.get(position).getbookId());

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

        StringRequest request = new StringRequest(Request.Method.POST, "https://optimumhealth.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(ViewPatientData.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ViewPatientData.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewPatientData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("bookid", id);
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

                                    String bookid = object.getString("book_id");
                                    String usern = object.getString("user_name");
                                    String userp = object.getString("user_phone");
                                    String doctor_type = object.getString("doctor_type");
                                    String doctor_name = object.getString("doctor_name");
                                    String book_date = object.getString("book_date");
                                    String book_time = object.getString("book_time");

                                    employee = new Employee(bookid,usern,userp,doctor_type,doctor_name,book_date,book_time);
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
                Toast.makeText(ViewPatientData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Data_Activity.class));
    }
}
