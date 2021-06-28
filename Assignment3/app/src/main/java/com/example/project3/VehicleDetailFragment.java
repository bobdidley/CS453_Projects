package com.example.project3;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleDetailFragment extends Fragment {

    private HashMap<String, String> vehicle_details;

    public VehicleDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vehicle_details = (HashMap<String, String>) getArguments().get("details");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle_detail, container, false);


        String model = vehicle_details.get("vehicle_make") + " - " + vehicle_details.get("model");
        String price = "$" + vehicle_details.get("price");
        String description = vehicle_details.get("veh_description");
        String date = vehicle_details.get("created_at");

        ((TextView) view.findViewById(R.id.vehicle_make_model)).setText(model);
        ((TextView) view.findViewById(R.id.vehicle_price)).setText(price);
        ((TextView) view.findViewById(R.id.vehicle_description)).setText(description);
        ((TextView) view.findViewById(R.id.created_at_date)).setText(date);
        ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.image);

        return view;
    }
}