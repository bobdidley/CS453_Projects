package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomGalleryAdapter extends BaseAdapter {
    private Context context;
    int[] animals;
    LayoutInflater inflater;

    public CustomGalleryAdapter(Context applicationContext, int[] animals){
        this.context = applicationContext;
        this.animals = animals;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount(){
        return animals.length;
    }

    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ImageView imageView = new ImageView(context);
        view = inflater.inflate(R.layout.fragment_image_preview,null);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(animals[i]);
        return icon;

    }

}
