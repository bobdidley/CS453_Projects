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
    int[] animals = {R.drawable.animal13, R.drawable.animal14, R.drawable.animal15,
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
        for (int animal : animals) {
            imageView = new ImageView(this);
            imageView.setImageResource(animal);
            simpleViewFlipper.addView(imageView);
        }

        //
        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(simpleViewFlipper.getDisplayedChild() < animals.length - 1) {
                    simpleViewFlipper.showNext();
//                    ++imagePosition;
                    btnPrev.setClickable(true);
                }
                else {
                    btnNext.setClickable(false);   // NOTE: this may never change
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simpleViewFlipper.getDisplayedChild() > 0){
                    simpleViewFlipper.showPrevious();
//                    --imagePosition;
                    btnNext.setClickable(true);
                }
                else {
                    btnPrev.setClickable(false);
                }
            }
        });

        int interval = 500;
        // Slide View Option
       slideview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // when the "Slide Show" checkbox is checked,
               // the slide show runs
               if(slideview.isChecked()){
                   btnNext.setClickable(false);
                   btnPrev.setClickable(false);
//                   simpleViewFlipper.setAutoStart(true);
                   simpleViewFlipper.setFlipInterval(interval);
                   simpleViewFlipper.startFlipping();

                   // NOTE: only prevents the slide show when displaying the last picture and checking the box
                   if(simpleViewFlipper.getDisplayedChild() >= animals.length - 1){
                       simpleViewFlipper.stopFlipping();
                       btnNext.setClickable(true);
                       btnPrev.setClickable(true);
                   }
               }
               // When the "Slide show" checkbox is unchecked
               // the slide show ends
               else{
                   simpleViewFlipper.stopFlipping();
                   btnNext.setClickable(true);
                   btnPrev.setClickable(true);
               }

           }
       });

    }








}