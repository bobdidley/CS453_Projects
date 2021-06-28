package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder> {

    private ArrayList<HashMap<String, String>> vehicleList;
    private final LayoutInflater inflater;

    /**
     * Overloaded constructor
     * @param context Context
     */
    public VehicleListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.vehicleList = new ArrayList<>();
    }

    /**
     * Clears the vehicle list array and updates so a new vehicle list can be put in.
     */
    public void reset() {
        this.vehicleList.clear();
        notifyDataSetChanged();
    }

    /**
     * Sets the new vehicle list.
     * @param vehicleList ArrayList<HashMap<String, String>>
     */
    public void setVehicleList(ArrayList<HashMap<String, String>> vehicleList) {
        this.vehicleList = vehicleList;
        notifyDataSetChanged();
    }

    /**
     * Creates the view for ViewHolder.
     * @param parent ViewGroup
     * @param viewType int
     * @return VehicleViewHolder
     */
    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.vehicle_list_content, parent, false);

        return new VehicleViewHolder(itemView, this);
    }

    /**
     * Binds the view holder and sets all the necessary values to the views.
     * @param holder VehicleViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        // instantiate necessary information

        // grab the necessary information
        String vehicle_id = vehicleList.get(position).get("id");
        String vehicle_price = vehicleList.get(position).get("price");
        String vehicle_mileage = vehicleList.get(position).get("mileage");

        TextView txt_id = holder.itemView.findViewById(R.id.id);
        TextView txt_price = holder.itemView.findViewById(R.id.price);
        TextView txt_mileage = holder.itemView.findViewById(R.id.mileage);

        // set the values to the views
        txt_id.setText(vehicle_id);
        txt_price.setText("$" + vehicle_price);
        txt_mileage.setText(vehicle_mileage + " miles");

    }

    /**
     * Returns the size of vehicleList.
     * @return int
     */
    @Override
    public int getItemCount() {
        return vehicleList.size();
    }


    public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView idTextView;
        public final TextView priceTextView;
        public final TextView mileageTextView;
        final VehicleListAdapter vehicleAdapter;

        /**
         * Overloaded constructor
         * @param itemView View
         * @param vehicleAdapter VehicleListAdapter
         */
        public VehicleViewHolder(View itemView, VehicleListAdapter vehicleAdapter) {
            super(itemView);

            this.idTextView = itemView.findViewById(R.id.id);
            this.priceTextView = itemView.findViewById(R.id.price);
            this.mileageTextView = itemView.findViewById(R.id.mileage);
            this.vehicleAdapter = vehicleAdapter;

            itemView.setOnClickListener(this);
        }

        /**
         * Sets the onClick functionality for each item in the list.
         * @param v View
         */
        @Override
        public void onClick(View v) {
            // communicate this selection to new window or second fragment to show vehicle details
            int position = getLayoutPosition();
            final MainActivity mParentActivity = (MainActivity) inflater.getContext();

            // check if in tablet mode
            if(mParentActivity.getResources().getBoolean(R.bool.isTablet)) {   // query the attrs.xml for boolean value
                // in tablet mode, show in side fragment
                VehicleDetailFragment frg = new VehicleDetailFragment();

                // send vehicle details through a Bundle
                Bundle args = new Bundle();
                args.putSerializable("details", vehicleList.get(position));
                frg.setArguments(args);

                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.vehicle_detail_container, frg)
                        .addToBackStack(null)
                        .commit();
            } else {
                // in phone mode, send to another window
                Intent detailActivity = new Intent(inflater.getContext(), VehicleDetailActivity.class);
                detailActivity.putExtra("details", vehicleList.get(position));
                inflater.getContext().startActivity(detailActivity);
            }
        }
    }
}
