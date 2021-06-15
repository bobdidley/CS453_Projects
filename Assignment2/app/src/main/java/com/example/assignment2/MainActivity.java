package com.example.assignment2;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    private ViewFlipper simpleViewFlipper;
    private GridView gridView;
    private ImageView imageView;
    private Button btnPrev, btnNext;
    private CheckBox chkSlide, chkGrid;
    int[] animals = {R.drawable.animal13, R.drawable.animal14, R.drawable.animal15,
            R.drawable.animal16, R.drawable.animal17, R.drawable.animal18, R.drawable.animal19,
            R.drawable.animal20,R.drawable.animal21,R.drawable.animal22,R.drawable.animal23,
            R.drawable.animal24};
    ArrayList<imageModel> arrayImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnPrev = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
        chkSlide = (CheckBox) findViewById(R.id.chkBoxShow);

        gridView = (GridView) findViewById(R.id.viewGrid);
        chkGrid = (CheckBox) findViewById(R.id.chkBoxGallery);
        arrayImage = new ArrayList<>();

        instantiateGridView();
        instantiateViewFlipper();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simpleViewFlipper.getDisplayedChild() < animals.length - 1) {
                    simpleViewFlipper.showNext();
                    btnPrev.setClickable(true);
                }
                else {
                    btnNext.setClickable(false);
                    Toast.makeText(context, "You've reached the end of the line.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simpleViewFlipper.getDisplayedChild() > 0) {
                    simpleViewFlipper.showPrevious();
                    btnNext.setClickable(true);
                }
                else {
                    btnPrev.setClickable(false);
                    Toast.makeText(context, "You've reached the end of the line.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        int interval = 3000;
        // Slide View Option
       chkSlide.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // when the "Slide Show" checkbox is checked,
               // must not be currently displaying last image,
               // then slide show runs
               if(chkSlide.isChecked() && simpleViewFlipper.getDisplayedChild() < animals.length - 1){
                   btnNext.setClickable(false);
                   btnPrev.setClickable(false);
                   //when slide show option is checked,
                   // gallery view checkbox is disable
                   chkGrid.setClickable(false);

                   simpleViewFlipper.setAutoStart(true);
                   simpleViewFlipper.setFlipInterval(interval);
                   simpleViewFlipper.startFlipping();

                   // Animates the transitions for each image and stops the flipping once at the end of the image list
                   instantiateAnimationListener();

               }
               // When the "Slide show" checkbox is unchecked
               // the slide show ends
               else{
                   simpleViewFlipper.stopFlipping();
                   // reset the animations so there is visual glitches
                   simpleViewFlipper.setInAnimation(null);
                   simpleViewFlipper.setOutAnimation(null);

                   btnNext.setClickable(true);
                   btnPrev.setClickable(true);
                   //when slide show option is unchecked,
                   // gallery view checkbox is available
                   chkGrid.setClickable(true);
               }

           }
       });

       chkGrid.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(chkGrid.isChecked()) {
                   btnNext.setClickable(false);
                   btnPrev.setClickable(false);
                   //when gallery view option is checked,
                   // slide show checkbox is disable
                   chkSlide.setClickable(false);
                   simpleViewFlipper.setVisibility(View.INVISIBLE);
//                   gridView.setAdapter(galleryAdapter);   // ERROR: app crashes here
                   gridView.setVisibility(View.VISIBLE);

               }
               else {
                   gridView.setVisibility(View.INVISIBLE);
                   simpleViewFlipper.setVisibility(View.VISIBLE);
                   //when gallery view option is checked,
                   //slide show checkbox is disable
                   chkSlide.setClickable(true);
                   btnNext.setClickable(true);
                   btnPrev.setClickable(true);
               }
           }
       });
    }

    /**
     * Instantiates the GridView object with the necessary information
     */
    private void instantiateGridView() {
        for (int j : animals) {
            imageModel imageModel = new imageModel();
            imageModel.setmThumbIds(j);
            // add in array list
            arrayImage.add(imageModel);
        }
        CustomGalleryAdapter galleryAdapter = new CustomGalleryAdapter(context, arrayImage);
        gridView.setAdapter(galleryAdapter);
    }

    /**
     * Instantiates the ViewFlipper object with the necessary information
     */
    private void instantiateViewFlipper() {
        for (int animal : animals) {
            imageView = new ImageView(this);
            imageView.setImageResource(animal);
            simpleViewFlipper.addView(imageView);
        }
    }

    /**
     * Instantiates the AnimationListener for simpleViewFlipper with the necessary information
     */
    private void instantiateAnimationListener() {
        simpleViewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_in_right));
        simpleViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_left));

        Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            // We only care about the end of the animation since the slide show must stop at the end
            public void onAnimationEnd(Animation animation) {
                if (simpleViewFlipper.getDisplayedChild() == animals.length - 1){
                    simpleViewFlipper.stopFlipping();
                    // reset the animations so there is no visual glitches
                    simpleViewFlipper.setInAnimation(null);
                    simpleViewFlipper.setOutAnimation(null);
                }
            }
        };

        simpleViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
    }

}