package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private TextView txtView1, txtView2, txtView3, txtView4, txtView5, txtView6, txtView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About");

        txtView1 = findViewById(R.id.line1);
        txtView2 = findViewById(R.id.line2);
        txtView3 = findViewById(R.id.line3);
        txtView4 = findViewById(R.id.line4);
        txtView5 = findViewById(R.id.line5);
        txtView6 = findViewById(R.id.line6);
        txtView7 = findViewById(R.id.line7);


        txtView1.setText("TO-DO LIST");
        txtView2.setText("Daily productivity app that keeps track of tasks.");
        txtView3.setText("Contributors: Fiona Le & James Austin Jr.");
        txtView4.setText("Sponsors: AJ Fahim");
        txtView5.setText("Version #: 1.0");
        txtView6.setText("Copyright: Summer 2021");
        txtView7.setText("Donate here: GimmeMoney.com");
    }
}