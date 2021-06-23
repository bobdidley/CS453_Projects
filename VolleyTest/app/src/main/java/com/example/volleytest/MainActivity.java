package com.example.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    TextView textDrop;
    ProgressDialog pDialog;
    Spinner spin_make;
    ArrayList<String> makes;

    //String[] makesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textview);
        textDrop = (TextView)findViewById(R.id.textDrop);

        spin_make = (Spinner)findViewById(R.id.spinner_make);
        new GetItem().execute();
        //spin_make.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) MainActivity.this);
    }

    private class GetItem extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait ...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                JSONObject object = new JSONObject(readJSON());
                JSONArray jsonArray = object.getJSONArray("test");
                //JSONArray jsonArray = object.getJSONArray("lists");

                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        makes = new ArrayList<String>();

                     JSONObject v = jsonArray.getJSONObject(i);

                      Iterator<String> it = v.keys();

                      while (it.hasNext()) {
                            String key = it.next();
                            String name = v.getString("vehicle_make");

                            textview.append(name);
                            makes.add(name);
                            // ArrayList<String> makes does not add all "vehicle_make" -- need to
                            makes.add("Hello");
                        }


                    }
                }}
            catch(JSONException e){
                    e.printStackTrace();
                }
                return null;
            }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
//
            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,
                    R.layout.simple_spinner_dropdown_item,R.id.textDrop, makes);
            spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spin_make.setAdapter(spinnerAdapter);


        }
    }

        public String readJSON() {
            String json = null;
            try {
                // Opening data.json file
                InputStream inputStream = getAssets().open("data.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                // read values in the byte array
                inputStream.read(buffer);
                inputStream.close();
                // convert byte to string
                json = new String(buffer, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
                return json;
            }
            return json;
        }
}

