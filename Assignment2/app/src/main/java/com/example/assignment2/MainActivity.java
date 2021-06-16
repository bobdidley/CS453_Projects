/**
 * This is the main screen, it contains two fragments (ImagePreview and ControlPanel) that provide an photo album
 * interface. All buttons, animations, and other necessary objects in between are created and displayed here.
 *
 * @file MainActivity.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/15/2021
 * @version 1.0
 */

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
    private int[] animals = {R.drawable.animal13, R.drawable.animal14, R.drawable.animal15,
            R.drawable.animal16, R.drawable.animal17, R.drawable.animal18, R.drawable.animal19,
            R.drawable.animal20,R.drawable.animal21,R.drawable.animal22,R.drawable.animal23,
            R.drawable.animal24};
    ArrayList<imageModel> arrayImage;


    /**
     * All the listeners for the buttons and their functionality are set upon creation.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML id connections
        context = getApplicationContext();
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnPrev = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
        chkSlide = (CheckBox) findViewById(R.id.chkBoxShow);
        gridView = (GridView) findViewById(R.id.viewGrid);
        chkGrid = (CheckBox) findViewById(R.id.chkBoxGallery);
        arrayImage = new ArrayList<>();

        // instantiation methods
        instantiateGridView();
        instantiateViewFlipper();

        // Next button represented by '>>' and stops showing next image if at the end of the image list
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

        // Previous button represented by '<<' and stops showing previous image if at the beginning of the image list
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


        // Slide View check box toggles an automatic flip through all images in the list
        int interval = 3000;
       chkSlide.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // when the "Slide Show" checkbox is checked,
               // must not be currently displaying last image,
               // then slide show runs
               if(chkSlide.isChecked()){
                   // must disable other buttons while slide show is in progress
                   btnNext.setClickable(false);
                   btnPrev.setClickable(false);
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
                   // reset the animations so there is no visual glitches
                   simpleViewFlipper.setInAnimation(null);
                   simpleViewFlipper.setOutAnimation(null);

                   // must enable other buttons when slide show is no longer in progress
                   btnNext.setClickable(true);
                   btnPrev.setClickable(true);
                   chkGrid.setClickable(true);
               }

           }
       });

       chkGrid.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(chkGrid.isChecked()) {
                   // must disable other buttons while gallery is displayed
                   btnNext.setClickable(false);
                   btnPrev.setClickable(false);
                   chkSlide.setClickable(false);

                   // switch the visibility ViewFlipper to GridView
                   simpleViewFlipper.setVisibility(View.INVISIBLE);
                   gridView.setVisibility(View.VISIBLE);
               }
               else {
                   // switch the visibility GridView to ViewFlipper
                   gridView.setVisibility(View.INVISIBLE);
                   simpleViewFlipper.setVisibility(View.VISIBLE);

                   // must enable other buttons when gallery is no longer displayed
                   chkSlide.setClickable(true);
                   btnNext.setClickable(true);
                   btnPrev.setClickable(true);
               }
           }
       });
    }

    /**
     * Instantiates the GridView object with the necessary information.
     */
    private void instantiateGridView() {
        for (int animalIndex : animals) {
            imageModel imageModel = new imageModel();
            imageModel.setmThumbIds(animalIndex);
            // add in array list
            arrayImage.add(imageModel);
        }
        CustomGalleryAdapter galleryAdapter = new CustomGalleryAdapter(context, arrayImage);
        gridView.setAdapter(galleryAdapter);
    }

    /**
     * Instantiates the ViewFlipper object with the necessary information.
     */
    private void instantiateViewFlipper() {
        for (int animalIndex : animals) {
            imageView = new ImageView(this);
            imageView.setImageResource(animalIndex);
            simpleViewFlipper.addView(imageView);
        }
    }

    /**
     * Instantiates the AnimationListener for simpleViewFlipper with the necessary information.
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
                    Toast.makeText(context, "Slide show over", Toast.LENGTH_SHORT).show();
                    // reset the animations so there is no visual glitches
                    simpleViewFlipper.setInAnimation(null);
                    simpleViewFlipper.setOutAnimation(null);
                }
            }
        };

        simpleViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
    }

}