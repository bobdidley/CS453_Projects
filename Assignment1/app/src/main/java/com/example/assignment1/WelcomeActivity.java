/**
 * This is the welcome page only accessible if the user successfully logs into their account.
 *
 * @file WelcomeActivity.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/07/2021
 * @version 1.0
 */

package com.example.assignment1;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView txt;
    private Button btnSignout;
    Context context;

    /**
     * On creation of the Welcome Activity, screen displays a welcome message to user.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt = (TextView) findViewById(R.id.txtUsernameDisplay);
        btnSignout = findViewById(R.id.btnSignout);
        context = getApplicationContext();

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        txt.setText(getString(R.string.hello, username));

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Goodbye, " + username + "!", Toast.LENGTH_SHORT).show();
                Intent signout = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(signout);
            }
        });

    }
}