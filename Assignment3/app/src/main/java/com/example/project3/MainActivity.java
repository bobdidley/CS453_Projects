package com.example.project3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project3.content.Vehicles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private RecyclerView rv;
    private boolean mTwoPane = false;
    private Spinner spin_make;
    private ArrayList<Vehicles.Vehicle> modelArrayList;
    // NOTE: consider reducing HTTP into it's own class
    // HTTP request variables
    private final static String URL_MAKES    = "https://thawing-beach-68207.herokuapp.com/carmakes";                       // available car makes
    private       static String URL_MODELS   = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/<make_id>";        // available car models for specific make
    private       static String URL_VEHICLES = "https://thawing-beach-68207.herokuapp.com/cars/<make>/<model>/<zipcode>";  // available vehicles for specific make and model
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> vehicleList;

    // debug
//    private String[] makes = {"", "Car1", "Car2"};
    private String[] models = {"", "Mod1", "Mod2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        /**
         * HTTP requests
         */
        vehicleList = new ArrayList<>();
        new GetVehicles().execute();

        /**
         * Spinner data initialization
         */
        spin_make = findViewById(R.id.spinner_make);
        Spinner spin_model = findViewById(R.id.spinner_model);
        spin_make.setOnItemSelectedListener(this);
        spin_model.setOnItemSelectedListener(this);
//        ArrayAdapter<String> aa_makes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, makes);
        ArrayAdapter<String> aa_models = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models);
//        spin_make.setAdapter(aa_makes);
        spin_model.setAdapter(aa_models);

        rv = findViewById(R.id.vehicle_list);
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
        // Spinner items are selected

        // Query the JSON data and display on fragment_vehicle_list
//        new GetVehicles().execute();
    }

    /**
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Asynchronous JSON data retrieval
     */
    private class GetVehicles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // before data is loaded in
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            HttpHandler httpHandler = new HttpHandler();
            String jsonStr = httpHandler.makeServiceCall(URL_MAKES);

            if(jsonStr != null) {
                try {
//                    JSONObject jsonObject = new JSONObject(jsonStr);   // JSON Object is already an array, don't need to distinguish from other arrays
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    //
                    modelArrayList = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); ++i) {
                        JSONObject details = jsonArray.getJSONObject(i);   // index the JSON array for each individual JSON object then derive information

                        HashMap<String, String> info = new HashMap<>();

                        Vehicles.Vehicle model = new Vehicles.Vehicle();
                        model.setVehicleID(details.getString("id"));
                        model.setVehicleMakes(details.getString("vehicle_make"));
                        modelArrayList.add(model);

                        // works for as many keys the JSON object has, I assume it works for all JSON forms
                        Iterator<String> it = details.keys();
                        while(it.hasNext()) {
                            String key = it.next();
                            String val = details.getString(key);
                            info.put(key, val);
                        }

                        vehicleList.add(info);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }


            // trying to set the JSON information into the Spinner options using ArrayAdapter
            ArrayList<String> makes = new ArrayList<>();
            //
            //
            for (int i = 0; i < modelArrayList.size(); i++){
                makes.add(modelArrayList.get(i).getVehicleMakes().toString());
            }

            //for(HashMap<String, String> i : vehicleList) {
            //    if(i.containsKey("vehicle_make")) {
            //        makes.add(i.get("vehicle_make"));
            //    }
            //}
            ArrayAdapter<String> aa_makes = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, makes);
            spin_make.setAdapter(aa_makes);   // NOTE: does not set information, either due to asynchronous issue or improper data linkage

            // does not work for all JSON forms
            // need a dynamic adapter for all JSON forms (eg. vehicles makes, vehicle models, vehicle details)
//            ListAdapter adapter = new SimpleAdapter(MainActivity.this, vehicleList, R.layout.simple_spinner_item,
//                    new String[] {"name", "email", "mobile"}, new int[] {R.id.make, R.id.model, R.id.vehicle_list});
//            rv.setAdapter(new SimpleItemRecyclerViewAdapter((List) vehicleList));   // display vehicle results
        }
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
            holder.mContentView.setText(mValues.get(position).vID);
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
