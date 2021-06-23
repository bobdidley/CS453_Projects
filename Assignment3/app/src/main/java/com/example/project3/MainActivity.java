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

import com.example.project3.content.Makes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    /********************************** PROJECT VARIABLES ************************************/
    // variables used for project functionality once test variables have been proven to work

    private RecyclerView rv;
    private boolean mTwoPane = false;   // used for tablet view compatibility
    private Spinner spin_make;
    private ArrayList<Makes.Make> modelArrayList;
    // NOTE: consider reducing HTTP into it's own class
    // HTTP request variables
    private final static String URL_MAKES    = "https://thawing-beach-68207.herokuapp.com/carmakes";                       // available car makes
    private       static String URL_MODELS   = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/<make_id>";        // available car models for specific make
    private       static String URL_VEHICLES = "https://thawing-beach-68207.herokuapp.com/cars/<make>/<model>/<zipcode>";  // available vehicles for specific make and model
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> vehicleList;

    /*******************************************************************************/


    /********************************** TEST VARIABLES ************************************/
    // test for debugging purposes

    private String[] makes = {"", "Car1", "Car2"};
    private String[] models = {"", "Mod1", "Mod2"};

    /*******************************************************************************/


    /********************************** ONCREATE ************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        /**
         * Spinner data initialization
         */
        spin_make = findViewById(R.id.spinner_make);
        Spinner spin_model = findViewById(R.id.spinner_model);
        spin_make.setOnItemSelectedListener(this);
        spin_model.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa_makes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, makes);
        ArrayAdapter<String> aa_models = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models);
        spin_make.setAdapter(aa_makes);
        spin_model.setAdapter(aa_models);

        rv = findViewById(R.id.vehicle_list);
        // debug
        // set rv adapter
    }

    /*******************************************************************************/


    /********************************** SPINNER ************************************/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Spinner items are selected

        // Query the JSON data and display on fragment_vehicle_list
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}   // Do nothing

    /*******************************************************************************/


    /********************************** JSON PARSE ************************************/

    /**
     * Asynchronous JSON data retrieval
     * Parsing JSON data for Makes spinner
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

                    for(int i = 0; i < jsonArray.length(); ++i) {
                        JSONObject details = jsonArray.getJSONObject(i);   // index the JSON array for each individual JSON object then derive information

                        HashMap<String, String> info = new HashMap<>();

                        // Could use this if we keep the Vehicles class
                        //Vehicles.Vehicle model = new Vehicles.Vehicle();
                        //model.setVehicleID(details.getString("id"));
                        //model.setVehicleMakes(details.getString("vehicle_make"));
                        //String id = details.getString("id");
                        //String make = details.getString("vehicle_make");
                        //info.put(id, make);
                        //makes[i] = info.get(make);


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

            // try to set the JSON information into the Spinner options
        }
    }

    /*******************************************************************************/


    /********************************** RECYCLER VIEW ADAPTER AND VIEW HOLDER ************************************/
    // need to create the ViewHolder and RecyclerView adapter
    // consider making the Spinners into ListViews for easier adapter code

    /*******************************************************************************/
}
