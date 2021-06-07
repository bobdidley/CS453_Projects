package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private Button m_btnSignup;
    private EditText m_txtUsername, m_txtPassword, m_txtRePassword, m_txtEmail, m_txtPhone;
    Context context;

    /**
     * On creation of Signup Activity, the user is prompted to input their information to sign up.
     * @param savedInstanceState Bundle
     */
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
                // Validate username field
                if(data.isEmpty(m_txtUsername)){
                    m_txtUsername.setError("Username is required!"); }
                else{
                    m_txtUsername.setError(null);}

                // Validate password field
                if (data.isEmpty(m_txtPassword)){
                        m_txtPassword.setError("Password is required!"); }
                else{
                    m_txtPassword.setError(null); }

                // Validate retype password field
                if (data.isEmpty(m_txtRePassword)) {
                        m_txtRePassword.setError("Retype Password is required!"); }
                else{
                    m_txtRePassword.setError(null);
                }

                //Validate email
                if (data.isEmpty(m_txtEmail)){
                        m_txtEmail.setError("Email is required"); }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    m_txtEmail.setError("Please enter a valid email address");
                }

                // Validate phone number
                if (data.isEmpty(m_txtPhone)){
                        m_txtPhone.setError("Input phone number is required"); }
                else if (!phone.matches("[0-9]{10}")){
                    m_txtPhone.setError("Invalid phone format");
                }
                else{
                    m_txtPhone.setError(null);
                }

                // Show toast message if one of the input fields are blanked
                if(data.isEmpty(m_txtUsername) || data.isEmpty(m_txtPassword) || data.isEmpty(m_txtRePassword)
                        || data.isEmpty(m_txtEmail) || data.isEmpty(m_txtPhone)){
                    Toast.makeText(context, "Please fill up all blank fields", Toast.LENGTH_SHORT).show();
                }
                // Validate if password and retype password match
                else if (!password.equals(rePassword)) {
                    Toast.makeText(context, "Please retype correct password", Toast.LENGTH_SHORT).show(); }
                // Show toast message if input email type or phone number are not in correct format
                else if((!phone.matches("[0-9]{10}") || (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) )){
                    Toast.makeText(context, "Please enter valid email or phone number", Toast.LENGTH_SHORT).show();
                }

                // when all the input fields meet requirements, signup activity successfully and redirect to the welcome page.
                else{
                    // Register new user
                    if(data.register(username, password, email, phone)) {
                        Toast.makeText(context, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                        // Intent signup --> redirect to Dashboard after clicking SIGN ME UP! Button
                        Intent signup = new Intent(SignupActivity.this, WelcomeActivity.class);

                        // Add username in welcome message
                        signup.putExtra("USERNAME", username);
                        startActivity(signup);
                    }
                    else
                        Toast.makeText(context, "Sign up not successful\nUsername is not unique", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}