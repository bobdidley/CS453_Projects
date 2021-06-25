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
 * Use the {@link VehicleDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HashMap<String, String> vehicle_details;

    public VehicleDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehicleDetailFragment newInstance(String param1, String param2) {
        VehicleDetailFragment fragment = new VehicleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.ic_launcher_background);

        return view;
    }
}