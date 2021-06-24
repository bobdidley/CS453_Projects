package com.example.project3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
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

import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    /********************************** PROJECT VARIABLES ************************************/
    // variables used for project functionality once test variables have been proven to work

    private RecyclerView rv;
    Context context;
    private boolean mTwoPane = false;   // used for tablet view compatibility

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

    HashMap<String, String> makes = new HashMap<>();   // can be used for id tag in url
    HashMap<String, String> models = new HashMap<>();   // can be used for id tag in url
//    ArrayList<HashMap<String, String>> vehicleList;

    boolean hasExecuted = false;

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

        new GetMakes().execute();

        rv = findViewById(R.id.vehicle_list);
        // debug
        // set rv adapter
    }

    /*******************************************************************************/


    /********************************** SPINNER ************************************/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Spinner items are selected
        if ((position == 0)) {
            ((TextView) view).setTextColor(Color.GRAY);
        } else {
//            ((TextView) view).setTextColor(Color.BLACK);
            --position;
        }
        /* makes.keySet().toArray()[position].toString() --> this translates the key set (make ids in the hashmap)
         *                                                   to an array then indexes that array with int position
         *                                                   to retrieve the key in the hashmap
         */
        Toast.makeText(context, "make_id = " + makes.keySet().toArray()[position].toString(), Toast.LENGTH_SHORT).show();

        // Query the JSON data and display on fragment_vehicle_list
        if(!hasExecuted) {
            Integer make_id = Integer.parseInt(makes.keySet().toArray()[position].toString());
            new GetModels().execute(make_id);
        }
        hasExecuted = true;
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

//        ProgressDialog pDialog;
//        Context context = spin_make.getContext();
        ArrayList<String> vehicle_makes = new ArrayList<>();
//        makes = new HashMap<String, String>();

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
                        // reduced code into the doJsonParsing method
//                        JSONObject details = jsonArray.getJSONObject(i);   // index the JSON array for each individual JSON object then derive information
//
//                        // NOTE: this code is reusable
//                        HashMap<String, String> info = new HashMap<>();
//
//                        // works for as many keys the JSON object has, I assume it works for all JSON forms
//                        Iterator<String> it = details.keys();
//                        while(it.hasNext()) {
//                            String key = it.next();
//                            String val = details.getString(key);
//                            info.put(key, val);
//
//                            // debug
//                            Log.i("Key = ", key);
//                            Log.i("Val = ", val);
//                        }
//                        Log.i("Info:", info.toString());

                        HashMap<String, String> info = doJsonParsing(jsonArray, i);

                        vehicle_makes.add(info.get("vehicle_make"));
                        makes.put(info.get("id"), info.get("vehicle_make"));
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
            doPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            HttpHandler httpHandler = new HttpHandler();

            // debug
            Log.i("URL Models", URL_MODELS);
            Log.i("Make Tag Index", "" + URL_MODELS.indexOf("<make_id>"));
            Log.i("Integer Params", "" + integers[0]);

            // Modify url string accordingly
            try {
//                URL_MODELS += integers[0];
                URL_MODELS = URL_MODELS.substring(0, URL_MODELS.indexOf("<make_id>")) + integers[0];
            } catch(IndexOutOfBoundsException idx) {
            }

            String jsonStr = httpHandler.makeServiceCall(URL_MODELS);

            // debug
            Log.i("URL Models", URL_MODELS);

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
            Log.i("Key = ", key);
            Log.i("Val = ", val);
        }
        Log.i("Info:", info.toString());

        return info;
    }

    /**
     * Common onPostExecute code
     * @param data ArrayList<String>
     * @param spin int
     */
    private void doPostExecute(ArrayList<String> data, int spin) {
        if(pDialog.isShowing()) {
            pDialog.dismiss();
        }

        // set array to spinner options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Index which spinner object you want to set the adapter to
        // NOTE: there may be an error where the adapter is not set to the spinner
        switch(spin) {
            case 0: spin_make.setAdapter(adapter); break;
            case 1: spin_model.setAdapter(adapter); break;
            default: Log.e("Spinner Adapter", "Spinner was not set to an adapter"); break;
        }
    }

    /*******************************************************************************/
}
