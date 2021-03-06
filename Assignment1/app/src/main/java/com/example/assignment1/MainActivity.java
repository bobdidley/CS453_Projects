/**
 * This is the main login page, the first page the user will see when opening the application. The user is prompted
 * for their account username and password to login.
 *
 * @file MainActivity.java
 * @authors Fiona Le & James Austin Jr.
 * @date 06/07/2021
 * @version 1.0
 */

package com.example.assignment1;

import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button m_btnLogin;
    private Button m_btnSignup;
    private EditText m_txtUsername;
    private EditText m_txtPassword;
    Context context;
    public static Data data = new Data();


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
            //HashMap<String, String> users = new HashMap<>(); // NOTE: temporary data storage, still need a data class
            //users.put("String", "string");
//            data.register("123", "123", "", "");

        m_btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                String checkUser = m_txtUsername.getText().toString();
                String checkPassword = m_txtPassword.getText().toString();


                // checks if credentials exist
//                if(users.size() != 0 && users.containsKey(checkUser) && users.get(checkUser).equals(checkPassword)) {
                if(!data.isEmpty() && data.isRegistered(checkUser, checkPassword)) {

                    // Intent login --> redirect to Dashboard after clicking Login Button
                    Intent login = new Intent(context, WelcomeActivity.class);

                    // Add username in welcome message
                    login.putExtra("USERNAME", checkUser);

                    // start login activity

                    startActivity(login);
                }
                else {
                    Toast.makeText(context, "Incorrect Credentials\nTry Again or Sign Up", Toast.LENGTH_SHORT).show();
                    m_txtPassword.setText("");
                }

                // check if input fields are not blanked, and show error message if the field is blanked
                if(data.isEmpty(m_txtUsername)){
                    m_txtUsername.setError("Username is required to login!");
                }

                if (data.isEmpty(m_txtPassword)){
                    m_txtPassword.setError("Password is required to login!");
                }



            }
        });

        m_btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Intent signup --> Redirect to signup page after clicking signup Button
                Intent signup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });
    }
}
