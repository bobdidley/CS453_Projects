package com.example.todolist;

import android.content.DialogInterface;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.todolist.db_contents.DBHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText currentPassword;
    private EditText updatePassword;
    private EditText updateRetypePassword;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPassword = findViewById(R.id.currentPassword);
        updatePassword = findViewById(R.id.updatePassword);
        updateRetypePassword = findViewById(R.id.txtRetypePassword);
        btn_update = findViewById(R.id.btnUpdate);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currPass = currentPassword.getText().toString();
                String upPass = updatePassword.getText().toString();
                String upRePass = updateRetypePassword.getText().toString();

                if(currPass.isEmpty()) {
                    currentPassword.setError("Missing field");
                }
                if(upPass.isEmpty()) {
                    updatePassword.setError("Missing field");
                }
                boolean retypeCheck = false;
                if (upRePass.isEmpty()) {
                    updateRetypePassword.setError("Missing field");
                } else if(upRePass.equals(upPass)) {
                    retypeCheck = true;
                } else {
                    updateRetypePassword.setError("Password does not match");
                }

                if(currentPassword.getError() == null && updatePassword.getError() == null
                        && updateRetypePassword.getError() == null && retypeCheck) {
                    DBHelper db = new DBHelper(getApplicationContext());

                    if (db.isExistingUser(db.getUsername(), currPass)) {
                        if (db.updateUserPassword(updatePassword.getText().toString(), Login.USER_ID)) {
                            Toast.makeText(ChangePasswordActivity.this,"Password is updated", Toast.LENGTH_SHORT)
                                    .show();
                            Intent profile = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                            startActivity(profile);
                        }
                    }
                } else {
                    new AlertDialog.Builder(ChangePasswordActivity.this)
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



    }
}