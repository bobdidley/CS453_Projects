/**
 * This is the adapter to allow multiple images be displayed in a grid view.
 *
 * @file CustomGalleryAdapter.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/15/2021
 * @version 1.0
 */

package com.example.assignment2;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.example.assignment2.R.layout.*;

public class CustomGalleryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<imageModel> animals;
    private LayoutInflater inflater;

    /**
     * Overloaded constructor
     * @param applicationContext Context
     * @param animals ArrayList<imageModel>
     */
    public CustomGalleryAdapter(Context applicationContext, ArrayList<imageModel> animals){
        this.context = applicationContext;
        this.animals = animals;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount(){
        return animals.size();
    }

    @Override
    public Object getItem(int i){
        return animals.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    /**
     * Instantiates ImageView and sets the image resource as the specified image.
     * @param i int
     * @param view View
     * @param viewGroup ViewGroup
     * @return View
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ImageView imageView;
        view = inflater.inflate(image_list,null);
        imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(animals.get(i).getmThumbIds());
        return view;
    }

}
