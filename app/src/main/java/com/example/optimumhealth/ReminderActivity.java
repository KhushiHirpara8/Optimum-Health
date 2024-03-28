package com.example.optimumhealth;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener{

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        // set Onclick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        //Set notificationId & text.
        Intent intent = new Intent (ReminderActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message", editText.getText().toString());

        // PendingIntent
         PendingIntent alarmIntent = PendingIntent.getBroadcast(ReminderActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);

         // AlarmManager
         AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:


                // Set alarm.
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                // set(type, milliseconds, intent)
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Reminder Set Successful!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn:
                // Cancel Alarm
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}