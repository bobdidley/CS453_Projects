package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project3.content.Vehicles;

public class VehicleDetailActivity extends AppCompatActivity {
    public Vehicles.Vehicle mVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        mVehicle = Vehicles.VEHICLE_ITEMS.get(getIntent().getIntExtra(Vehicles.VEHICLE_ID_KEY, 0));

        if(mVehicle!=null){
            ((TextView)findViewById(R.id.vehicle_detail)).setText(mVehicle.details);
        }
    }
}