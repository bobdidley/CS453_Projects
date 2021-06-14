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
    ArrayList<imageModel> animals;
    LayoutInflater inflater;

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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ImageView imageView;
        view = inflater.inflate(image_list,null);
        imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(animals.get(i).getmThumbIds());
        return view;
    }

}
