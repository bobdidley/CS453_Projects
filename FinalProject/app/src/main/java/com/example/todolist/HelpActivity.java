package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private TextView txtView1, txtView2, txtView3, txtView4, txtView5, txtView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        txtView1 = findViewById(R.id.line1);
        txtView2 = findViewById(R.id.line2);
        txtView3 = findViewById(R.id.line3);
        txtView4 = findViewById(R.id.line4);
        txtView5 = findViewById(R.id.line5);
        txtView6 = findViewById(R.id.line6);


        txtView1.setText("We dont even know how to fix it, so you are on your own");
        txtView2.setText("Contact us at 1-800-NEVER OR email us at never.ever@help.com");
        txtView3.setText("FAQ");
        txtView4.setText("How do you work this app? - idk");
        txtView5.setText("Who forced you to build this?- AJFahim");
        txtView6.setText("How much is the pro version? - Free.99");
      

    }
}