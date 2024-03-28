package com.example.optimumhealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookdataActivity extends AppCompatActivity {

    ListView listView;
    MyuAdapter adapter;




    public static ArrayList<Employeeu> employeeArrayList = new ArrayList<>();
    String url = "https://optimumhealth.000webhostapp.com/retrieveuser.php";

    Employeeu employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdata);

        listView = findViewById(R.id.myListView);
        adapter = new MyuAdapter(this,employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data"};
                builder.setTitle(employeeArrayList.get(position).getUserName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetailUserActivity.class)
                                        .putExtra("position",position));
                                break;
                        }
                    }
                });

                builder.create().show();

            }
        });

        retrieveData();


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

                                    employee = new Employeeu(bookid,usern,userp,doctor_type,doctor_name,book_date,book_time);
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
                Toast.makeText(BookdataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Intent intent = getIntent();
            String username = intent.getStringExtra("username");
            Map<String,String> params = new HashMap<String,String>();
            params.put("username",username);
            return params;
        }
    };


        RequestQueue requestQueue = Volley.newRequestQueue(BookdataActivity.this);
        requestQueue.add(request);


    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),FindDoctorActivity.class));
    }
}
