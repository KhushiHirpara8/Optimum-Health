package com.example.optimumhealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.denzcoskun.imageslider.ImageSlider;
import java.util.List;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;

public class AboutUs extends AppCompatActivity {
    // Urls of our images.
    ImageView twitter1,map1,email1,call1,website1,linkedin1,facebook1,insta1,yt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ImageSlider imageSlider=findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();

        //slideModels.add(new SlideModel(R.drawable.bed));
        //slideModels.add(new SlideModel(R.drawable.waiting));
        //slideModels.add(new SlideModel(R.drawable.general));

        slideModels.add(
                new SlideModel(R.drawable.bed,
                        ScaleTypes.FIT)
        );
        slideModels.add(
                new SlideModel(R.drawable.waiting,
                        ScaleTypes.FIT)
        );
        slideModels.add(
                new SlideModel(R.drawable.general,
                        ScaleTypes.FIT)
        );


        imageSlider.setImageList(slideModels);


        twitter1 = findViewById(R.id.twitter);
        twitter1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://www.twitter.com/infynno");
            }
        });

        map1 = findViewById(R.id.map);
        map1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://goo.gl/maps/dL9bAwBbJKn9L9Hv9");
            }
        });

        email1 = findViewById(R.id.email);
        email1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://mail.google.com/mail/u/0/#inbox?compose=CllgCJTNqwpPBzzfjnKmlRMLKhzbxPDCPFCPNTrdptNCWDxnMMSpRMPBMVsJNZqXqqSSNlRjDXB");
            }
        });

        website1 = findViewById(R.id.website);
        website1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://infynno.com");
            }
        });

        linkedin1 = findViewById(R.id.linkedin);
        linkedin1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://www.linkedin.com/company/infynno-solutions/");
            }
        });

        facebook1 = findViewById(R.id.facebook);
        facebook1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://www.facebook.com/infynnosolutions");
            }
        });

        insta1 = findViewById(R.id.insta);
        insta1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://www.instagram.com/infynno_solutions/");
            }
        });

        yt = findViewById(R.id.youtube);
        yt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gotoUrl("https://www.youtube.com/channel/UC1ywSWy2n5FGoVLOpENhxXA?app=desktop");
            }
        });
    }

    private void gotoUrl(String s)
    {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
