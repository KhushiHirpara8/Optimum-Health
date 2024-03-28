package com.example.optimumhealth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private  static final String CHANNEL_ID = "SAMPLE_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get id & message from intent.
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        // When notification is tapped, call MainActivity.
        //Intent mainIntent = new Intent(context, MainActivity.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        // NotificationManager
        NotificationManager NotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            // For API 26 and above
            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channel_name, importance);
            NotificationManager.createNotificationChannel(channel);



        // Prepare notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.alarm);
                builder.setContentTitle("It's Time!");
                builder.setContentText(message);
                //builder.setContentIntent(contentIntent);
                builder.setWhen(System.currentTimeMillis());
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setAutoCancel(true);

        // Notify
        NotificationManager.notify(notificationId, builder.build());


    }
}
