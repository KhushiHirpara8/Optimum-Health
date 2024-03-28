package com.example.optimumhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    ImageView rclogo,bgapp,cloverimg,aboutus,dietplan,bookappo,reminder,logotake,yourbook;
    LinearLayout splashtext,hometext,menus;
    Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frombottom=AnimationUtils.loadAnimation(this,R.anim.frombottom);

        logotake = findViewById(R.id.logotake);
        bgapp= findViewById(R.id.bgapp);
        cloverimg= findViewById(R.id.bgapp);
        splashtext= findViewById(R.id.splashtext);
        hometext= findViewById(R.id.hometext);
        menus= findViewById(R.id.menus);
        bookappo= findViewById(R.id.bookappo);
        reminder=findViewById(R.id.reminder);
        aboutus=findViewById(R.id.aboutus);
        yourbook=findViewById(R.id.yourbook);
        dietplan=findViewById(R.id.dietplan);

        bgapp.animate().translationY(-1200).setDuration(1000).setStartDelay(700);
        cloverimg.animate().alpha(0).setDuration(1000).setStartDelay(800);
        splashtext.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(200);

        hometext.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        bookappo.setOnClickListener(view -> {

            Intent it = getIntent();
            String username = it.getStringExtra("username");
            String phone = it.getStringExtra("phone");
            Intent intent=new Intent(this,FindDoctorActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("phone",phone);
            startActivity(intent);
        });

        reminder.setOnClickListener(view -> {
            Intent intent=new Intent(this,ReminderActivity.class);
            startActivity(intent);
        });

        aboutus.setOnClickListener(view -> {
            Intent intent=new Intent(this,AboutUs.class);
            startActivity(intent);

        });

        logotake.setOnClickListener(view -> {
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);

        });

        dietplan.setOnClickListener(view -> {
            Intent intent=new Intent(this,DietplanActivity.class);
            startActivity(intent);

        });

        yourbook.setOnClickListener(view -> {
            Intent it = getIntent();
            String username = it.getStringExtra("username");
            Intent intent=new Intent(this,BookdataActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);

        });


    }
}
