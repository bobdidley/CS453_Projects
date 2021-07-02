package com.example.todolist.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CalendarView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.todolist.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.CustomTaskAdapter;
import com.example.todolist.Login;
import com.example.todolist.db_contents.DBHelper;

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
                selectedDate = "";
                if(month < 10) selectedDate += 0;
                selectedDate += (month+1) + "/";
                if(dayOfMonth < 10) selectedDate += 0;
                selectedDate += dayOfMonth + "/";
                if(year < 10) selectedDate += 0;
                selectedDate += year;

                // debug
//                Log.i("Select Date", "Selected Date = " + selectedDate);
//                Toast.makeText(getContext(), selectedDate, Toast.LENGTH_SHORT).show();

                setRecyclerView(db.getDateTasks(Login.USER_ID, selectedDate));
            }
        });

        return view;
    }

    /**
     * Sets the recycler view with the appropriate data.
     * @param taskList ArrayList<HashMap<String, String>>
     */
    private void setRecyclerView(ArrayList<HashMap<String, String>> taskList) {

        // debug
//        Log.i("Calendar RV", "Setting the recycler view");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        // necessary for initial set up
        if(adapter == null) { adapter = new CustomTaskAdapter(getActivity(), taskList); }
        else {
            adapter.reset();
            adapter.setTasksList(taskList);
        }
        recyclerView.setAdapter(adapter);
    }
}
