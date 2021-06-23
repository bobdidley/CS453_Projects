package com.example.p3_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private RequestQueue mQueue;
    private ArrayList<String> names = new ArrayList<String>();
    private List<String> name = new ArrayList<>();
    private Spinner spinner_make;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_make = findViewById(R.id.spinner_make);

        tv = findViewById(R.id.textview);
        mQueue = Volley.newRequestQueue(MainActivity.this);
        jsonParse();

    }
    private void jsonParse(){
        String url ="https://api.androidhive.info/contacts/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("contacts");

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String name = obj.getString("name");
                                names.add(name);
                                tv.append(name);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,
                                R.layout.simple_spinner_layout,names);
                        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        spinner_make.setAdapter(spinnerAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);
    }

}