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
    ArrayList<imageModel> arrayImage;

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
//        ImageView imageView;
//
//        if(view == null) {
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setPadding(5,5,5,5);
//        }
//        else {
//            imageView = (ImageView) view;
//        }
//        imageView.setImageBitmap(Bitmap.decode(animals[i].getAbsoluteFile()));
//
//        ImageView imageView = new ImageView(context);

//        view = inflater.inflate(R.layout.fragment_image_preview, null);
//        ImageView grid = view.findViewById(R.id.viewGrid);
//        grid.setImageResource(animals[i]);

//        return grid;

    }

}
