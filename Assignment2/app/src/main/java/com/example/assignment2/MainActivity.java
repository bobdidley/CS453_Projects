package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper simpleViewFlipper;
    ImageView imageView;
    private Button btnPrev, btnNext;
    private CheckBox slideview;
    int i =0;
    int animals[] = {R.drawable.animal13, R.drawable.animal14, R.drawable.animal15,
            R.drawable.animal16, R.drawable.animal17, R.drawable.animal18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnPrev = (Button)findViewById(R.id.btnPrevious);
        btnNext = (Button)findViewById(R.id.btnNext);
        slideview =(CheckBox)findViewById(R.id.chkBoxShow);


        //
        for(int i=0; i<animals.length;i++){
            imageView = new ImageView(this);
            imageView.setImageResource(animals[i]);
            simpleViewFlipper.addView(imageView);
        }

        //
        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                simpleViewFlipper.showNext();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i!=(animals.length)){
                    simpleViewFlipper.showPrevious();}
            }
        });

        // Slide View Option
       slideview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // when the "Slide Show" checkbox is checked,
               // the slide show runs
               if(slideview.isChecked()){
                   simpleViewFlipper.setAutoStart(true);
                   simpleViewFlipper.setFlipInterval(3000);
                   simpleViewFlipper.startFlipping();
               }
               // When the "Slide show" checkbox is unchecked
               // the slide show ends
               else{
                   simpleViewFlipper.stopFlipping();
               }

           }
        });



    }








}