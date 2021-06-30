package com.example.todolist;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.todolist.db_contents.DBHelper;

public class Login extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btn_signup;
    private Button btn_login;
    public static int USER_ID;

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
                DBHelper db = new DBHelper(getApplicationContext());
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if(username.equals("")) { edtUsername.setError("Missing field"); }
                if(password.equals("")) { edtPassword.setError("Missing field"); }

                if(db.isExistingUser(username, password)) {
                    // Intent home --> Redirect to home page after clicking login Button
                    edtPassword.setText("");   // empty out password field for security purposes
                    USER_ID = db.getUserId(username);

                    // debug
                    Log.i("USER ID", "USER_ID = " + USER_ID);

                    Intent home = new Intent(Login.this, ProfileActivity.class);
//                    Intent home = new Intent(Login.this, HomeActivity.class);
                    startActivity(home);
                } else {
                    // sets an alert message if the credentials do not exist in the database
                    new AlertDialog.Builder(Login.this)
                            .setTitle("Invalid Credentials")
                            .setMessage("The account credentials are not correct or do not exist, try again.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setIconAttribute(android.R.attr.alertDialogIcon)
                            .show();
                }
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