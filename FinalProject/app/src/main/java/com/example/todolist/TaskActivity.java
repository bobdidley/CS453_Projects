package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaskActivity extends AppCompatActivity {

    private FloatingActionButton addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        addTask = findViewById(R.id.btnAddTask);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(TaskActivity.this, AddTaskActivity.class);
                startActivity(addTask);
            }
        });


    }
}