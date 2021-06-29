package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.todolist.db_contents.Task;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}