package com.example.project3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project3.content.Makes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    /********************************** PROJECT VARIABLES ************************************/
    // variables used for project functionality once test variables have been proven to work

    private RecyclerView rv;
    private ArrayAdapter<String> spinAdapter;
    private VehicleListAdapter vehicleAdapter;
    Context context;
    private boolean mTwoPane = false;   // used for tablet view compatibility, should be used in VehicleListAdapter

    private Spinner spin_make;
    private Spinner spin_model;

    // NOTE: consider reducing HTTP into it's own class
    // HTTP request variables
    private final static String URL_MAKES    = "https://thawing-beach-68207.herokuapp.com/carmakes";                       // available car makes
    private       static String URL_MODELS   = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/<make_id>";        // available car models for specific make
    private       static String URL_VEHICLES = "https://thawing-beach-68207.herokuapp.com/cars/<make_id>/<model_id>/<zipcode>";  // available vehicles for specific make and model
    String zipcode = "92603";

    ProgressDialog pDialog;

    /*******************************************************************************/


    /********************************** TEST VARIABLES ************************************/
    // test for debugging purposes

//    private String[] makes = {"", "Car1", "Car2"};
//    private String[] models = {"", "Mod1", "Mod2"};

    LinkedHashMap<String, String> makes = new LinkedHashMap<>();   // can be used for id tag in url
    LinkedHashMap<String, String> models = new LinkedHashMap<>();   // can be used for id tag in url
    ArrayList<HashMap<String, String>> vehicleList = new ArrayList<>();   // NOTE: insertion order is not preserved

    boolean hasExecuted = true;

    /*******************************************************************************/


    /********************************** ONCREATE ************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        context = getApplicationContext();

        /**
         * Spinner data initialization
         */
        spin_make = findViewById(R.id.spinner_make);
        spin_model = findViewById(R.id.spinner_model);
        spin_make.setOnItemSelectedListener(this);
        spin_model.setOnItemSelectedListener(this);
        // no longer need this
//        ArrayAdapter<String> aa_makes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, makes);
//        ArrayAdapter<String> aa_models = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, models);
//        spin_make.setAdapter(aa_makes);
//        spin_model.setAdapter(aa_models);

        // default values for first position since adapter array has a default too
        makes.put("", "");
        models.put("", "");

        new GetMakes().execute();

        vehicleAdapter = new VehicleListAdapter(MainActivity.this);
        rv = findViewById(R.id.vehicle_list);
        // debug
        // set rv adapter
    }

    /*******************************************************************************/


    /********************************** SPINNER LOGIC ************************************/

    Integer make_id = -1;
    Integer model_id = -1;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//        if(vehicleAdapter != null && vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); } //vehicleAdapter = null; }
//        if(vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); } //vehicleAdapter = null; }

        vehicleAdapter.reset();

        if (parent.getId() == spin_make.getId()){
            // Spinner items are selected
            if (position == 0) {   // checks if the option position is the first default option, and does not let execute
                ((TextView) view).setTextColor(Color.GRAY);
                hasExecuted = true;

                // debug
//                Log.i("Position 0", "On the first spinner option");
            } else if (position > 0) {   // position must not be default
                hasExecuted = false;

                /* makes.keySet().toArray()[position].toString() --> this translates the key set (make ids in the hashmap)
                 *                                                   to an array then indexes that array with int position
                 *                                                   to retrieve the key in the hashmap
                 */
                make_id = Integer.parseInt(makes.keySet().toArray()[position].toString());

//                if(vehicleAdapter != null && vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); }
//                vehicleList.clear();
//                if(vehicleAdapter != null) vehicleAdapter.notifyDataSetChanged();

                // debug
                Log.i("hasExecuted", "Not on the default position");
                Log.i("Selected Make", "" + make_id);
                Log.i("Makes Key Set", makes.keySet().toString());
//                Toast.makeText(context, "make_id = " + make_id, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "position = " + position, Toast.LENGTH_SHORT).show();
            }

            // debug
            Log.i("hasExecuted", "" + hasExecuted);

            // Query the JSON data and display on fragment_vehicle_list
            if (!hasExecuted) {   // check if the GetModels has already executed to avoid endless loop
                // debug
                Log.i("Models Execute", "The GetModels().execute() has been called");

                new GetModels().execute(make_id);
            }
            hasExecuted = true;   // NOTE: the program technically doesn't need this b/c the endless loop
                                  //       is caught fairly quick but let's keep it for safety purposes
        }
        else if(parent.getId() == spin_model.getId()) {
            // NOTE: the Models spinner may need its own hasExecuted boolean variable
            if(position == 0) {
                ((TextView) view).setTextColor(Color.GRAY);
                hasExecuted = true;

            } else if(position > 0) {
                hasExecuted = false;
                model_id = Integer.parseInt(models.keySet().toArray()[position].toString());

//                if(vehicleAdapter != null && vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); }
//                vehicleList.clear();
//                if(vehicleAdapter != null) vehicleAdapter.notifyDataSetChanged();

            }

            // debug
            Log.i("Models Key Set", models.keySet().toString());
            Log.i("Make ID", "" + make_id);
            Log.i("Model ID", "" + model_id);
            Toast.makeText(context, "make_id = " + make_id, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "model_id = " + model_id, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "position = " + position, Toast.LENGTH_SHORT).show();

            if (!hasExecuted) {   // check if the GetModels has already executed to avoid endless loop
                // debug
                Log.i("Vehicle Execute", "The GetVehicles().execute() has been called");

                new GetVehicles().execute(make_id, model_id);
            }
            hasExecuted = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}   // Do nothing

    /*******************************************************************************/


    /********************************** JSON PARSE FOR MAKES ************************************/

    /**
     * Asynchronous JSON data retrieval
     * Parsing JSON data for Makes spinner
     */
    private class GetMakes extends AsyncTask<Void, Void, Void> {

        ArrayList<String> vehicle_makes = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            vehicle_makes.add("Select a make");
            doPreExecute();
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
                        HashMap<String, String> info = doJsonParsing(jsonArray, i);

                        vehicle_makes.add(info.get("vehicle_make"));
                        makes.put(info.get("id"), info.get("vehicle_make"));

                        // debug
//                        Log.i("Make:", makes.toString());
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

            doPostExecute(vehicle_makes, 0);
        }
    }

    /*******************************************************************************/


    /********************************** JSON PARSE FOR MODELS ************************************/

    private class GetModels extends AsyncTask<Integer, Void, Void> {

        ArrayList<String> vehicle_models = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            vehicle_models.add("Select a model");
            models.clear();
            models.put("", "");

            doPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            HttpHandler httpHandler = new HttpHandler();

            // debug
//            Log.i("URL Models Before", URL_MODELS);
//            Log.i("Make Tag Index", "" + URL_MODELS.indexOf("<make_id>"));
//            Log.i("Integer Params", "" + integers[0]);

            // Modify url string accordingly
            try {
//                URL_MODELS += integers[0];
                URL_MODELS = URL_MODELS.replace("<make_id>", integers[0].toString());
            } catch(IndexOutOfBoundsException ignored) {
            }

            // debug
//            Log.i("URL Models Modified", URL_MODELS);

            String jsonStr = httpHandler.makeServiceCall(URL_MODELS);

            // Revert the URL string change
            try {
                URL_MODELS = URL_MODELS.substring(0, URL_MODELS.lastIndexOf(integers[0].toString())) + "<make_id>";
            } catch(IndexOutOfBoundsException ignored) {
            }

            // debug
//            Log.i("URL Models After", URL_MODELS);

            if(jsonStr != null) {
                try {
//                    JSONObject jsonObject = new JSONObject(jsonStr);   // JSON Object is already an array, don't need to distinguish from other arrays
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for(int i = 0; i < jsonArray.length(); ++i) {
                        HashMap<String, String> info = doJsonParsing(jsonArray, i);

                        vehicle_models.add(info.get("model"));
                        models.put(info.get("id"), info.get("model"));
                    }
                    // debug
//                    Log.i("End For", "Reached the end of the for-loop");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            doPostExecute(vehicle_models, 1);
            Log.i("Finish", "onPostExecute() finishes here");
        }
    }

    private class GetVehicles extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            if(vehicleAdapter != null && vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); }
//            vehicleList.clear();

            doPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            HttpHandler httpHandler = new HttpHandler();

            // debug
            Log.i("URL Vehicles Before", URL_VEHICLES);
//            Log.i("Make Tag Index", "" + URL_VEHICLES.indexOf("<make_id>"));
//            Log.i("Model Tag Index", "" + URL_VEHICLES.indexOf("<model_id>"));
//            Log.i("Zip Tag Index", "" + URL_VEHICLES.indexOf("<zipcode>"));
//            Log.i("Integer Params", "" + integers.toString());

            // for reference
//            "https://thawing-beach-68207.herokuapp.com/cars/<make_id>/<model_id>/<zipcode>";

            // Modify url string accordingly
            try {
                URL_VEHICLES = URL_VEHICLES.replace("<make_id>", integers[0].toString());
                URL_VEHICLES = URL_VEHICLES.replace("<model_id>", integers[1].toString());
                URL_VEHICLES = URL_VEHICLES.replace("<zipcode>", zipcode);
            } catch(IndexOutOfBoundsException ignored) {
            }

            // debug
            Log.i("URL Vehicles Modified", URL_VEHICLES);

            String jsonStr = httpHandler.makeServiceCall(URL_VEHICLES);

            // Revert the URL string change from reverse to not affect forward index
            try {
                URL_VEHICLES = URL_VEHICLES.replace(zipcode, "<zipcode>");
//                URL_VEHICLES = URL_VEHICLES.substring(0, URL_VEHICLES.lastIndexOf(integers[1].toString())) + "<model_id>";

                int int1_index = URL_VEHICLES.lastIndexOf(integers[1].toString());
                int replace1_len = integers[1].toString().length();

                // debug
//                Log.i("INT1 idx", "" + int1_index);
//                Log.i("INT1 len", "" + replace1_len);

                URL_VEHICLES = URL_VEHICLES.substring(0, int1_index) + "<model_id>" + URL_VEHICLES.substring(int1_index + replace1_len);

                int int0_index = URL_VEHICLES.lastIndexOf(integers[0].toString());
                int replace0_len = integers[0].toString().length();

                // debug
//                Log.i("INT0 idx", "" + int0_index);
//                Log.i("INT0 len", "" + replace0_len);

                URL_VEHICLES = URL_VEHICLES.substring(0, int0_index) + "<make_id>" + URL_VEHICLES.substring(int0_index + replace0_len);
            } catch(IndexOutOfBoundsException ignored) {
            }

            // debug
            Log.i("URL Vehicles After", URL_VEHICLES);

            if(jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);                // create the JSON object
                    JSONArray jsonArray = jsonObject.getJSONArray("lists");   // index for the necessary JSON array with "lists"

                    // debug
//                    Log.i("JSON Array Contents", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); ++i) {
                        // NOTE: insertion order is not preserved
                        //       if we care for insertion order of the key value pairs then use LinkedHashMap
                        //       but this should not be a problem
                        HashMap<String, String> info = doJsonParsing(jsonArray, i);

//                        vehicle_vehicles.add(info.get("model"));
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

            // debug
            if(vehicleAdapter != null) { Log.i("Vehicle Count", "" + vehicleAdapter.getItemCount()); }

//            if(vehicleAdapter != null && vehicleAdapter.getItemCount() > 0) { vehicleAdapter.reset(); }   // resets everytime

//            VehicleListAdapter vehicleAdapter = new VehicleListAdapter(MainActivity.this, vehicleList);]
            vehicleAdapter.setVehicleList(vehicleList);
            rv.setAdapter(vehicleAdapter);

            if(vehicleAdapter != null) { Log.i("Vehicle Count", "" + vehicleAdapter.getItemCount()); }

//            VehicleListAdapter adapt;
//            adapt = new VehicleListAdapter(MainActivity.this, vehicleList);
            //rv.setLayoutManager(new LinearLayoutManager(this));
//            adapt.reset();
//            rv.setAdapter(adapt);

            //doPostExecute(vehicleList, -1);
            //Log.i("Finish", "onPostExecute() finishes here");
        }
    }

    /*******************************************************************************/


    /********************************** RECYCLER VIEW ADAPTER AND VIEW HOLDER ************************************/
    // need to create the ViewHolder and RecyclerView adapter
    // consider making the Spinners into ListViews for easier adapter code

    /*******************************************************************************/


    /********************************** REUSABLE CODE ************************************/

    /**
     * Common onPreExecute code
     */
    private void doPreExecute() {
        // before data is loaded in
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     * Parses through the JSON array and returns the HashMap containing the information associated to an item
     * @param jsonArray JSONArray
     * @param i int
     * @return HashMap<String, String>
     * @throws JSONException
     */
    private HashMap<String, String> doJsonParsing(JSONArray jsonArray, int i) throws JSONException {
        JSONObject details = jsonArray.getJSONObject(i);   // index the JSON array for each individual JSON object then derive information

        // NOTE: this code is reusable
        HashMap<String, String> info = new HashMap<>();

        // works for as many keys the JSON object has, I assume it works for all JSON forms
        Iterator<String> it = details.keys();
        while(it.hasNext()) {
            String key = it.next();
            String val = details.getString(key);
            info.put(key, val);

            // debug
//            Log.i("Key = ", key);
//            Log.i("Val = ", val);
        }
//        Log.i("Info:", info.toString());

        return info;
    }

    /**
     * Common onPostExecute code
     * @param data ArrayList<String>
     * @param setter int
     */
    private void doPostExecute(ArrayList<String> data, int setter) {
        if(pDialog.isShowing()) {
            pDialog.dismiss();
        }

        // Index which spinner object you want to set the spinAdapter to
        // NOTE: there may be an error where the spinAdapter is not set to the spinner
        switch(setter) {
            case 0:   // spin_make
                spinAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, data);
                spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin_make.setAdapter(spinAdapter);
                break;
            case 1:   // spin_model
                spinAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, data);
                spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin_model.setAdapter(spinAdapter);
                break;
            case 2:   // vehicleList
//                vehicleAdapter = new VehicleListAdapter(MainActivity.this, vehicleList);
                vehicleAdapter.setVehicleList(vehicleList);
                rv.setAdapter(vehicleAdapter);
                break;
            default: Log.w("Adapter", "Adapter was not set to anything"); break;
        }
    }

    /*******************************************************************************/
}
