package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.todolist.db_contents.DBHelper;
import com.example.todolist.db_contents.User;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtRetypePass;
    private Button btnSignup;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        db = new DBHelper(getApplicationContext());

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtRetypePass = findViewById(R.id.txtRetypePassword);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String retypePass = txtRetypePass.getText().toString();

        if (username.equals("")) {
            txtUsername.setError("Missing field");
        }
        if (password.equals("")) {
            txtPassword.setError("Missing field");
        }
        boolean retypeCheck = false;
        if (retypePass.equals("")) {
            txtRetypePass.setError("Missing field");
        } else if(retypePass.equals(txtPassword.getText().toString())) {
            retypeCheck = true;
        } else {
            txtRetypePass.setError("Password does not match");
        }

        if (txtUsername.getError() == null && txtPassword.getError() == null && txtRetypePass.getError() == null && retypeCheck) {
            if(db.insertUser(new User(username, password))) {

                // debug
//                Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();

                // send to login screen with credentials filled in
                Intent login = new Intent(Signup.this, Login.class);
                login.putExtra("username", username);
                startActivity(login);
            } else {
                // sets an alert message if the username already exists in the database
                new AlertDialog.Builder(Signup.this)
                        .setTitle("Error - Username")
                        .setMessage("This username already exists, input a different username.")
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
    }
}