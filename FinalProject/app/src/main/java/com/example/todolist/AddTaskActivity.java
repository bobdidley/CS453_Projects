package com.example.todolist;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolist.db_contents.Task;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button addTask;
    private EditText task_name;
    private EditText task_category;
    private Spinner task_priority;
    private Spinner task_time;
//    private DatePicker datePicker;
//    private Calendar calendar;

    private final String[] times = {"Select a time", "12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM",
            "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM","12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
            "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM"};
    private final String[] priorities = {"Select a priority (1=high, 5=low)", "1", "2", "3", "4", "5"};

    // Task details
    private String name;
    private String date;   // may need to be a String value
    private String category;   // can this be made an enum instead?
    private int priority;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setTitle("Create a Task");

        task_name = findViewById(R.id.txtTaskName);
        task_category = findViewById(R.id.txtTaskCategory);
        task_priority = findViewById(R.id.spinnerPriority);
        task_time = findViewById(R.id.spinnerTime);

        addTask = findViewById(R.id.btnAddTask);

        task_priority.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, priorities));
        task_time.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times));

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = task_name.getText().toString();
                category = task_category.getText().toString();

                if(name.equals("")) { task_name.setError("Missing field"); }
                if(category.equals("")) { task_category.setError("Missing field"); }
                boolean pryCheck = true;
                boolean timeCheck = true;
                if(task_priority.getSelectedItem().equals(priorities[0])) {
                    ((TextView) task_priority.getSelectedView()).setError(priorities[0]);
                    pryCheck = false;
                }
                if(task_time.getSelectedItem().equals(times[0])) {
                    ((TextView) task_time.getSelectedView()).setError(times[0]);
                    timeCheck = false;
                }

                if(task_name.getError() == null && task_category.getError() == null && pryCheck && timeCheck) {
                    Intent addTask = new Intent(AddTaskActivity.this, ProfileActivity.class);
                    startActivity(addTask);
                } else {
                    // sets an alert message if the Create a Task form is not complete
                    new AlertDialog.Builder(AddTaskActivity.this)
                            .setTitle("Incomplete Form")
                            .setMessage("The task details are not complete or are of improper format.")
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            switch (view.getId()) {
                case R.id.spinnerPriority:
                    priority = Integer.parseInt(task_priority.getSelectedItem().toString());
                    break;
                case R.id.spinnerTime:
                    time = task_time.getSelectedItem().toString();
                    break;
                default:
                    Log.i("Spinner Selected", "No spinner options were selected");
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}