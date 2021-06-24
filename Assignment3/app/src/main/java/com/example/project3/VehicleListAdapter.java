package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project3.content.Vehicles;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder> {

    private ArrayList<HashMap<String, String>> vehicleList;
    private LayoutInflater inflater;

    public VehicleListAdapter(Context context, ArrayList<HashMap<String, String>> vehicleList) {
        this.inflater = LayoutInflater.from(context);
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.vehicle_list_content, parent, false);

        return new VehicleViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        // instantiate other necessary information

        String vehicle_id = vehicleList.get(position).get("id");
//        String vehicle_name = vehicleList.get(position).get("id");

        TextView txt_id = holder.itemView.findViewById(R.id.id);
//        TextView txt_name = holder.itemView.findViewById(R.id.name);

        txt_id.setText(vehicle_id);   // need to make a TextView out of itemView to set text
//        txt_content.setText(vehicle_name);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView idTextView;
        final VehicleListAdapter vehicleAdapter;

        public VehicleViewHolder(View itemView, VehicleListAdapter vehicleAdapter) {
            super(itemView);

            this.idTextView = itemView.findViewById(R.id.id);
            // instantiate the other TextView views here
            this.vehicleAdapter = vehicleAdapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();

            // communicate this selection to new window or second fragment to show vehicle details
            // transfer information to onPostExecute maybe? idk
        }
    }
}
