package com.example.todolist;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btn_signup;
    private Button btn_login;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btn_signup = findViewById(R.id.btnSignup);
        btn_login = findViewById(R.id.btnLogin);

        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Intent signup --> Redirect to signup page after clicking signup Button
                Intent signup = new Intent(Login.this, Signup.class);
                startActivity(signup);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // need to check if username and password exist in database, redirect to home page if they do

                // Intent home --> Redirect to home page after clicking login Button
//                Intent home = new Intent(Login.this, Home.class);
//                startActivity(home);
            }
        });

        // auto fills in username after sign up is complete
        if(getIntent().hasExtra("username")) {
            // debug
//            Log.i("Extras Null", "Extras is not null, intent received");

            String login_username = getIntent().getStringExtra("username");
            edtUsername.setText(login_username);
        }
    }
}