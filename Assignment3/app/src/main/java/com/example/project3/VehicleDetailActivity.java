package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project3.content.Vehicles;

public class VehicleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);


//        ((TextView)findViewById(R.id.vehicle_detail)).setText(mVehicle.details);
    }
}