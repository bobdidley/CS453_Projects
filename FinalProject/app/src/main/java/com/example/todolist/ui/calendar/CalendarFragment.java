package com.example.todolist.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CalendarView;

import android.widget.Button;
import android.widget.Spinner;

import android.widget.TextView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;


import com.example.todolist.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.CustomTaskAdapter;
import com.example.todolist.Login;
import com.example.todolist.R;
import com.example.todolist.db_contents.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


import java.text.SimpleDateFormat;
import java.util.Date;


public class CalendarFragment extends Fragment {

    private CalendarViewModel slideshowViewModel;
    CustomTaskAdapter adapter;
    private Spinner categoryFilter;
    private Spinner priorityFilter;
    private FloatingActionButton add;
    private Button btn_remove;
    private RecyclerView recyclerView;

    ArrayList<String> pry_filters;
    ArrayList<String> cat_filters;

    private DBHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container,false);

        CalendarView calendar = view.findViewById(R.id.calendarView);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String selectedDate = sdf.format(new Date(calendar.getDate()));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getContext(), month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
        
        recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);

        db = new DBHelper(getContext());
        setRecyclerView(0, null, -1);

        return view;
    }

    private void setRecyclerView(int filter, @Nullable String category, @Nullable int priority) {
        ArrayList<HashMap<String, String>> taskList = new ArrayList<>();
        switch(filter) {
            case 0:
                taskList = db.getUserTasks(Login.USER_ID);
               // setSpinners(taskList);
                break;
            case 1:
                taskList = db.getCategoryTasks(Login.USER_ID, category);
                break;
            case 2:
                taskList = db.getPriorityTasks(Login.USER_ID, priority);
                break;
        }
//        updateSpinners(taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
//        adapter = new CustomTaskAdapter(getActivity(), taskList);
//        adapter.notifyDataSetChanged();
        if(adapter == null) { adapter = new CustomTaskAdapter(getActivity(), taskList); }
        else {
            adapter.reset();
            adapter.setTasksList(taskList);
        }
        recyclerView.setAdapter(adapter);
    }
}
