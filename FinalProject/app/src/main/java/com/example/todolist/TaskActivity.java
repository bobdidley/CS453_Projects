package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private Button addTask;
    private RecyclerView taskRecyclerView;
    private CustomTaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        getSupportActionBar().hide();


        //
        Intent intent = getIntent();
        HashMap<String,String> task_details = (HashMap<String, String>) intent.getSerializableExtra("detail");


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(TaskActivity.this, AddTaskActivity.class);
                startActivity(addTask);
            }
        });


    }
}