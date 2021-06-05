package com.example.assignment1;

import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button m_btnLogin;
    private Button m_btnSignup;
    private EditText m_txtUsername;
    private EditText m_txtPassword;
    Context context;
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        m_btnLogin = findViewById(R.id.btnLogin);
        m_btnSignup = findViewById(R.id.btnSignup);
        m_txtUsername = findViewById(R.id.username); // NOTE: txtUsername (signup) ≠ username (main)
        m_txtPassword = findViewById(R.id.password); // NOTE: txtPassword (signup) ≠ password (main)

        // debug
//        HashMap<String, String> users = new HashMap<>(); // NOTE: temporary data storage, still need a data class
//        users.put("String", "string");
        data.register("123", "123", "", "");

        m_btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                String checkUser = m_txtUsername.getText().toString();
                String checkPassword = m_txtPassword.getText().toString();
                // checks if credentials exist
//                if(users.size() != 0 && users.containsKey(checkUser) && users.get(checkUser).equals(checkPassword)) {
                if(!data.isEmpty() && data.isRegistered(checkUser, checkPassword)) {
                    // Intent login --> redirect to Dashboard after clicking Login Button
                    Intent login = new Intent(context, WelcomeActivity.class);
                    startActivity(login);
                }
                else {
                    Toast.makeText(context, "Incorrect Credentials\nTry Again or Sign Up", Toast.LENGTH_SHORT).show();
                    m_txtPassword.setText("");
                }
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