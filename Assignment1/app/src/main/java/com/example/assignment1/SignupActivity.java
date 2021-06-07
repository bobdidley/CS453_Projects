package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private Button m_btnSignup;
    private EditText m_txtUsername, m_txtPassword, m_txtRePassword, m_txtEmail, m_txtPhone;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Data data =(Data)getApplication();

        context =  getApplicationContext();
        m_btnSignup = findViewById(R.id.btnSignUp2);
        m_txtUsername = findViewById(R.id.txtUsername);
        m_txtPassword = findViewById(R.id.txtPassword);
        m_txtRePassword = findViewById(R.id.txtRetypePassword);
        m_txtEmail = findViewById(R.id.txtEmail);
        m_txtPhone = findViewById(R.id.txtPhone);

        m_btnSignup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username = m_txtUsername.getText().toString();
                String password = m_txtPassword.getText().toString();
                String rePassword = m_txtRePassword.getText().toString();
                String email = m_txtEmail.getText().toString();
                String phone = m_txtPhone.getText().toString();

                // Check if input fields are blanked, and show message error if any field is blanked.
                if(data.isEmpty(m_txtUsername)){
                    m_txtUsername.setError("Username is required!"); }

                if (data.isEmpty(m_txtPassword)){
                        m_txtPassword.setError("Password is required!"); }

                if (data.isEmpty(m_txtRePassword)) {
                        m_txtRePassword.setError("Retype Password is required!"); }

                if (data.isEmpty(m_txtEmail)){
                        m_txtEmail.setError("Email is required"); }

                if (data.isEmpty(m_txtPhone)){
                        m_txtPhone.setError("Input phone number is required"); }

                if (!rePassword.equals(password)){
                    Toast.makeText(context, "Retyped Password and Password must be matched", Toast.LENGTH_SHORT).show();
                    m_txtRePassword.setError("Please retype password again");
                }
                if(data.isEmpty(m_txtUsername) || data.isEmpty(m_txtPassword) || data.isEmpty(m_txtRePassword)
                        || data.isEmpty(m_txtEmail) || data.isEmpty(m_txtPhone)){
                    Toast.makeText(context, "Please fill up all blank fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    // Intent signup --> redirect to Dashboard after clicking SIGN ME UP! Button
                    Intent signup = new Intent(SignupActivity.this, WelcomeActivity.class);

                    // Add username in welcome message
                    signup.putExtra("USERNAME", username);
                    startActivity(signup);

                    // Register new user
                    data.register(username, password, email, phone);
                    Toast.makeText(context, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}