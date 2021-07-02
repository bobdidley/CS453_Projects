/**
 * App starts here for however much time set then redirects to login page.
 *
 * @file SplashWindow.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_window);

        getSupportActionBar().hide();
        Handler handler = new Handler();

        // redirects to login page once time is up
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashWindow.this, Login.class);
                startActivity(intent);
                SplashWindow.this.finish();
            }
        }, 3000);
    }
}