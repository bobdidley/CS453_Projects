package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView txt;

    /**
     * On creation of the Welcome Activity, screen displays a welcome message to user.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt = (TextView) findViewById(R.id.txtUsernameDisplay);

        Intent intent= getIntent();
        String username = intent.getStringExtra("USERNAME");
        txt.setText(getString(R.string.hello, username));

    }
}