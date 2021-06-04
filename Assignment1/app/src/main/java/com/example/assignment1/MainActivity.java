package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button m_btnLogin;
    private Button m_btnSignup;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        m_btnLogin = findViewById(R.id.btnLogin);
        m_btnSignup = findViewById(R.id.btnSignup);

        m_btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){

                // Intent login --> rediredct to Dashboard after clicking Login Button
                Intent login = new Intent(context, WelcomeActivity.class);
                startActivity(login);
            }
        });

        m_btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Intent signup --> Redirect to signup page after clicking signup Button
                Intent signup = new Intent(context, SignupActivity.class);
                startActivity(signup);
            }


        });
    }
}