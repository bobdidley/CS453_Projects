package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todolist.databinding.ActivityHomeBinding;
import com.example.todolist.databinding.ActivityProfileBinding;
import com.example.todolist.ui.gallery.TaskFragment;

public class HomeActivity extends AppCompatActivity {

    private Button btn_changePassword;
    private Button btn_editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        btn_changePassword = findViewById(R.id.btnChangePassword);
//        btn_editTask = findViewById(R.id.btnEditTasks);

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