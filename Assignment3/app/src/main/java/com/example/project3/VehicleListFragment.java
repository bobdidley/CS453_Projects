package com.example.project3;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project3.content.Vehicles;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class VehicleListFragment extends Fragment {

    public Vehicles.Vehicle mVehicle;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    public VehicleListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")

    public static VehicleListFragment newInstance(int selectedVehicle)
    {
        VehicleListFragment frg = new VehicleListFragment();
        Bundle arguments = new Bundle();

        arguments.putInt(Vehicles.VEHICLE_ID_KEY, selectedVehicle);
        frg.setArguments(arguments);
        return frg;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Vehicles.VEHICLE_ID_KEY)) {
            mVehicle = Vehicles.VEHICLE_ITEMS.get(getArguments().getInt(Vehicles.VEHICLE_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_list, container, false);
       // View view = inflater.inflate(R.layout.vehicle_detail, container, false);

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
////            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(Vehicles.ITEMS));
//        }
        if(mVehicle!=null){
            ((TextView) view.findViewById(R.id.vehicle_detail)).setText(mVehicle.details);
        }
        return view;
    }




}