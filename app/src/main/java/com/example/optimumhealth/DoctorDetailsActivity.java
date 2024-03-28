package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

public class DoctorDetailsActivity extends AppCompatActivity {

//    private String[][] doctor_details1 =
//            {
//                    {"Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No:9898989898", "600"},
//                    {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898", "980"},
//                    {"Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:8898989898", "300"},
//                    {"Doctor Name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:9898000000", "500"},
//                    {"Doctor Name: Ashok Panda", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "800"}
//            };
//
//    private String[][] doctor_details2 =
//            {
//                    {"Doctor Name: Mahesh babu", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No:9898989898", "600"},
//                    {"Doctor Name: Sarad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898", "780"},
//                    {"Doctor Name: Shanaya Roy", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:8898989898", "300"},
//                    {"Doctor Name: Rajesh Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:9898000000", "700"},
//                    {"Doctor Name: Ravi Pandia", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "900"}
//            };
//
//    private String[][] doctor_details3 =
//            {
//                    {"Doctor Name: Darshan Trivedi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No:9898989898", "600"},
//                    {"Doctor Name: Umang Hirpara", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898", "1080"},
//                    {"Doctor Name: Dhrumil Koshiya", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:8898989898", "350"},
//                    {"Doctor Name: Bhargav Patel", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:9898000000", "300"},
//                    {"Doctor Name: Rajiv Desai", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "700"}
//            };
//
//    private String[][] doctor_details4 =
//            {
//                    {"Doctor Name: Jeram Lukhi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No:9898989898", "650"},
//                    {"Doctor Name: Panav Dobariya", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898", "980"},
//                    {"Doctor Name: Mukund Dogra", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:8898989898", "500"},
//                    {"Doctor Name: Bhupendra Patel", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:9898000000", "400"},
//                    {"Doctor Name: Keshav Vyas", "Hospital Address: Katrai", "Exp: 7yrs", "Mobile No:7798989898", "450"}
//            };
//
//    private String[][] doctor_details5 =
//            {
//                    {"Doctor Name: Jayesh Mania", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No:9898989898", "608"},
//                    {"Doctor Name: Jay Pokia", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898", "900"},
//                    {"Doctor Name: Naresh Apte", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:8898989898", "300"},
//                    {"Doctor Name: Karmin Siyora", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:9898000000", "500"},
//                    {"Doctor Name: Dharmesh Kakadiya", "Hospital Address: Katrai", "Exp: 8yrs", "Mobile No:7798989898", "800"}
//            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    ListView listview;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonAHBack);
        listview = (ListView)findViewById(R.id.listViewDD);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
                finish();
            }
        });
        getData();

//        if(title.compareTo("Family Physicians")==0)
//            doctor_details = doctor_details1;
//        else{
//        if(title.compareTo("Allergists")==0)
//            doctor_details = doctor_details2;
//        else{
//        if(title.compareTo("Dentist")==0)
//            doctor_details = doctor_details3;
//        else{
//        if(title.compareTo("Surgeon")==0)
//            doctor_details = doctor_details4;
//        else {
//            doctor_details = doctor_details5;
//        }}}}

//
//        list = new ArrayList();
//        for(int i=0; i<doctor_details.length; i++) {
//            item= new HashMap<String, String>();
//            item.put( "line1", doctor_details[i][0]);//type
//            item.put( "line2", doctor_details[i][1]);//name
//            item.put( "line3", doctor_details[i][2]);//exp
//            item.put( "line4", doctor_details[i][3]);//mob
//            item.put("line5", "Cons Fees: " + doctor_details[i][4] + "/-");
//            list.add( item );
//        }
//        sa = new SimpleAdapter(this,list,
//                R.layout.multi_lines,
//                new String[]{line2","line3","line4","line5"},
//                new int[]{R.id.line_a,R.id.line_b,R.id.line_d,R.id.line_e,});
//
//        ListView lst = findViewById(R.id.listViewDD);
//        lst.setAdapter(sa);
//

//
//    }
}
    private void getData() {


        Intent it = getIntent();
        String title = it.getStringExtra("title");
        String value = title;


        String url = Config5.DATA_URL + title;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DoctorDetailsActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }


    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config5.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String drid = jo.getString(Config5.DR_ID);
                String drname = jo.getString(Config5.DR_NAME);
                String drtype = jo.getString(Config5.DR_TYPE);
                String drexp = jo.getString(Config5.DR_EXP);
                String drmob = jo.getString(Config5.DR_MOB);
                String drfees = jo.getString(Config5.DR_FEES);

                Intent it = getIntent();
                String title = it.getStringExtra("title");
                String value = title;

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
                        Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                        it.putExtra("text1", title);
                        //it.putExtra("text2", doctor_details[i][0]);
                        it.putExtra("text2", drname);
//                it.putExtra("text3", doctor_details[i][2]);
//                it.putExtra("text4", doctor_details[i][3]);
                        Intent it2 = getIntent();
                        String username = it2.getStringExtra("username");
                        String phone = it2.getStringExtra("phone");
                        it.putExtra("username",username);
                        it.putExtra("phone",phone);

                        startActivity (it);
                    }
                });

                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config5.DR_NAME, drname);
                employees.put(Config5.DR_EXP, drexp);
                employees.put(Config5.DR_MOB, drmob);
                employees.put(Config5.DR_FEES, drfees);
                employees.put(Config5.DR_ID, drid);

                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                DoctorDetailsActivity.this, list, R.layout.multi_lines,
                new String[]{Config5.DR_NAME, Config5.DR_EXP, Config5.DR_MOB, Config5.DR_FEES},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_d, R.id.line_e});





        listview.setAdapter(adapter);

    }
}

