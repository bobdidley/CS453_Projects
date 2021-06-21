package com.example.project3;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project3.content.Vehicles;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private boolean mTwoPane = false;
    private String[] makes = {"", "Car1", "Car2"};
    private String[] models = {"", "Mod1", "Mod2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        Spinner spin_make = findViewById(R.id.spinner_make);
        spin_make.setOnItemSelectedListener(this);
        Spinner spin_model = findViewById(R.id.spinner_model);
        spin_model.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa_makes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, makes);
        spin_make.setAdapter(aa_makes);
        ArrayAdapter<String> aa_models = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models);
        spin_model.setAdapter(aa_models);

        RecyclerView rv = findViewById(R.id.list);
        rv.setAdapter(new SimpleItemRecyclerViewAdapter(Vehicles.VEHICLE_ITEMS));

        // is the container layout available? If so, set mTwoPane to true

    }

    /**
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
