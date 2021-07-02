/**
 *
 *
 * @file HomeActivity.java
 * @authors Fiona Le & James Austin Jr.
 * @date 07/01/2021
 * @version 1.0
 */

package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {

    private Button btn_changePassword;
//    private Button btn_editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        btn_changePassword = findViewById(R.id.btnChangePassword);
//        btn_editTask = findViewById(R.id.btnEditTasks);

        // redirects to change password activity
        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePassword = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(changePassword);
            }
        });

//        btn_editTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent editTask = new Intent(HomeActivity.this, TaskActivity.class);
//                startActivity(editTask);
//            }
//        });


    }
}