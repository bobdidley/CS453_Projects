package com.example.todolist;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
        if (txtUsername.getText().toString().equals("")) {
            txtUsername.setError("Missing field");
        }
        if (txtPassword.getText().toString().equals("")) {
            txtPassword.setError("Missing field");
        }
        boolean retype = false;
        if (txtRetypePass.getText().toString().equals("")) {
            txtRetypePass.setError("Missing field");
        } else if(txtRetypePass.getText().toString().equals(txtPassword.getText().toString())) {
            retype = true;
        } else {
            txtRetypePass.setError("Password does not match");
        }

        if (txtUsername.getError() == null && txtPassword.getError() == null && txtRetypePass.getError() == null && retype) {
//            db.insertUser(new User(txtUsername.getText().toString(), txtPassword.getText().toString()));

            // debug
            Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
        }
    }
}