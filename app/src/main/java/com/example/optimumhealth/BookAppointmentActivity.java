package com.example.optimumhealth;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import java.util.*;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.*;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.Time;
import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1;
    EditText userphone;
    TextView tv,userd;
    // --Commented out by Inspection (05/01/2023 13:13):ProgressBar progressBar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        Button btnBook = findViewById(R.id.buttonBookAppointment);
        Button button = findViewById(R.id.buttonAppBack);
        userd = findViewById(R.id.userName);
        userphone=findViewById(R.id.phone);
        ed1.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String userdn = "Your Name : "+it.getStringExtra("username");

        tv.setText (title);
        ed1.setText (fullname);
        userd.setText(userdn);

        //datepicker
        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this, DoctorDetailsActivity.class));
            }
        });

        //timepicker
        initTimePicker();

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctor_type,doctor_name;
                String book_date;
                String book_time;

                book_time=timeButton.getText().toString();
                book_date=dateButton.getText().toString();
                doctor_name=fullname;
                doctor_type=title;
                Intent intent=getIntent();
                String username=intent.getStringExtra("username");

                String phone=userphone.getText().toString();

              //  Toast.makeText(getApplicationContext(), "You clicked button book appo", Toast.LENGTH_SHORT).show();
                if(!username.equals("") && !phone.equals("") && !doctor_name.equals("") && !doctor_type.equals("") && !book_time.equals("") && !book_date.equals("")) {
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[6];
                            field[0] = "username";
                            field[1] = "phone";
                            field[2] = "doctorname";
                            field[3] = "doctortype";
                            field[4] = "booktime";
                            field[5] = "bookdate";
                            String[] data = new String[6];
                            data[0] = username;
                            data[1] = phone;
                            data[2] = fullname;
                            data[3] = title;
                            data[4] = book_time;
                            data[5] = book_date;

                            PutData putData = new PutData("https://optimumhealth.000webhostapp.com/appointmentbooking.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                   //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("You have already booked this doctor on same date, try another date")) {
                                        Toast.makeText(getApplicationContext(), "You have already booked this doctor on same date, try another date", Toast.LENGTH_SHORT).show();
                                    }
                                    if(result.equals("sorry! That Day's bookings are full !! try for another day")) {
                                        Toast.makeText(getApplicationContext(), "sorry! That Day's bookings are full !! try for another day", Toast.LENGTH_SHORT).show();
                                    }
                                    if(result.equals("Your appointment is booked Sucessfully")){
                                            Toast.makeText(getApplicationContext(), "Your appointment is booked Sucessfully", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Error while booking", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Error while sending your data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required in a right format", Toast.LENGTH_SHORT).show();
                }
                            // remain : booking appointment is going to store in orderActivity
            }
        });
    }
        private void initDatePicker () {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    i1 = i1 + 1;
                    dateButton.setText(i2 + "/" + i1 + "/" + i);
                }
            };

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int weekd = cal.get(Calendar.DAY_OF_WEEK);

                int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
                datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

        }
        private void initTimePicker () {
            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    timeButton.setText(i + ":" + i1);
                }
            };
            Calendar cal = Calendar.getInstance();
            int hrs = cal.get(Calendar.HOUR);
            int mins = cal.get(Calendar.MINUTE);

            int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
            timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
        }

}