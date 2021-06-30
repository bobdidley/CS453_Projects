package com.example.todolist;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolist.db_contents.Task;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private Button addTask;
//    private EditText task_title;
//    private EditText task_category;
    private Spinner task_priority;
    private Spinner task_time;
//    private EditText task_time;
//    private EditText task_date;
//    private DatePicker datePicker;
//    private Calendar calendar;

    private String[] times = {"Select a time", "12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM",
            "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM","12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
            "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM"};
    private String[] priorities = {"Select a priority (1=high, 5=low)", "1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setTitle("Create a Task");

        task_priority = findViewById(R.id.spinnerPriority);
        task_time = findViewById(R.id.spinnerTime);
        addTask = findViewById(R.id.btnAddTask);

        task_priority.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, priorities));
        task_time.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times));
        task_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        task_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(addTask);
            }
        });
    }
}