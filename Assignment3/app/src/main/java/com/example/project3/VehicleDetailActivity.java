package com.example.project3;

import android.content.Intent;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class VehicleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        Intent intent = getIntent();
        HashMap<String, String> vehicle_details = (HashMap<String, String>) intent.getSerializableExtra("details");

        String model = vehicle_details.get("vehicle_make") + " - " + vehicle_details.get("model");
        String price = "$" + vehicle_details.get("price");
        String description = vehicle_details.get("veh_description");
        String date = vehicle_details.get("created_at");

        ((TextView) findViewById(R.id.vehicle_make_model)).setText(model);
        ((TextView) findViewById(R.id.vehicle_price)).setText(price);
        ((TextView) findViewById(R.id.vehicle_description)).setText(description);
        ((TextView) findViewById(R.id.created_at_date)).setText(date);
        ((ImageView) findViewById(R.id.image)).setImageResource(R.drawable.image);
    }
}