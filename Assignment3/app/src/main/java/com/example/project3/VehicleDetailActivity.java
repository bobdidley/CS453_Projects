/**
 * The window where vehicle details are displayed after a vehicle on the vehicle list is selected.
 * Used in phone mode.
 *
 * @file VehicleDetailActivity.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/27/2021
 * @version 1.0
 */

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

        // grab the arguments sent via Intent
        Intent intent = getIntent();
        HashMap<String, String> vehicle_details = (HashMap<String, String>) intent.getSerializableExtra("details");

        // derive the necessary information
        String model = vehicle_details.get("vehicle_make") + " - " + vehicle_details.get("model");
        String price = "$" + vehicle_details.get("price");
        String description = vehicle_details.get("veh_description");
        String date = vehicle_details.get("created_at");

        // set the values to the views
        ((TextView) findViewById(R.id.vehicle_make_model)).setText(model);
        ((TextView) findViewById(R.id.vehicle_price)).setText(price);
        ((TextView) findViewById(R.id.vehicle_description)).setText(description);
        ((TextView) findViewById(R.id.created_at_date)).setText(date);
        ((ImageView) findViewById(R.id.image)).setImageResource(R.drawable.image);
    }
}