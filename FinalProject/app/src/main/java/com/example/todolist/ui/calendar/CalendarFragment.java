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

    CustomTaskAdapter adapter;
    private RecyclerView recyclerView;
    private String selectedDate;

    private DBHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container,false);

        CalendarView calendar = view.findViewById(R.id.calendarView);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        selectedDate = sdf.format(new Date(calendar.getDate()));
        recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);
        db = new DBHelper(getContext());
        setRecyclerView(db.getDateTasks(Login.USER_ID, selectedDate));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = sdf.format(new Date(year, month, dayOfMonth).getDate());
                Toast.makeText(getContext(), selectedDate, Toast.LENGTH_SHORT).show();
                setRecyclerView(db.getDateTasks(Login.USER_ID, selectedDate));
            }
        });
        
        return view;
    }

    private void setRecyclerView(ArrayList<HashMap<String, String>> taskList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        if(adapter == null) { adapter = new CustomTaskAdapter(getActivity(), taskList); }
        else {
            adapter.reset();
            adapter.setTasksList(taskList);
        }
        recyclerView.setAdapter(adapter);
    }
}
