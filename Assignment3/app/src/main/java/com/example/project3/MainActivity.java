package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project3.content.Vehicles;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);


        RecyclerView rv = findViewById(R.id.list);
        rv.setAdapter(new SimpleItemRecyclerViewAdapter(Vehicles.VEHICLE_ITEMS));

        // is the container layout available? If so, set mTwoPane to true

    }

    class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter <ViewHolder>{

        private final List<Vehicles.Vehicle> mValues;
        SimpleItemRecyclerViewAdapter(List<Vehicles.Vehicle> items) { mValues = items; }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_content,
                   parent, false);


            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position +1));
            holder.mContentView.setText(mValues.get(position).title);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        Vehicles.Vehicle mItem;
        final TextView mIdView;
        final TextView mContentView;
        final View mView;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mIdView = itemView.findViewById(R.id.id);
            mContentView = itemView.findViewById(R.id.content);
        }
    }
}
