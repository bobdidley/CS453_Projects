package com.example.todolist.ui.gallery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.*;
import com.example.todolist.databinding.FragmentTaskBinding;
import com.example.todolist.db_contents.DBHelper;
import com.example.todolist.ui.calendar.CalendarFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    CustomTaskAdapter adapter;
    ArrayList<HashMap<String,String>> data;
    private java.util.ArrayList<HashMap<String, String>> ArrayList;
    private Spinner categoryFilter;
    private Spinner priorityFilter;
    private FloatingActionButton add;
    private Button btn_remove;
    private RecyclerView recyclerView;

    ArrayList<String> pry_filters;
    ArrayList<String> cat_filters;

    private DBHelper db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_task, container,false);
        categoryFilter = view.findViewById(R.id.spnCategoryFilter);
        priorityFilter = view.findViewById(R.id.spnPriorityFilter);
       add = view.findViewById(R.id.btnAddTask);
       btn_remove = view.findViewById(R.id.btnRemoveTask);
       recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);
        cat_filters = new ArrayList<>();
        pry_filters = new ArrayList<>();
       cat_filters.add("Category Filter");
       pry_filters.add("Priority Filter");

       db = new DBHelper(getContext());

       ArrayList<HashMap<String, String>> taskList = db.getUserTasks(Login.USER_ID);
        for(HashMap<String, String> task : db.getUserTasks(Login.USER_ID)) {
            String pry = task.get(DBHelper.TASKS_COL_PRIORITY);
            String cat = task.get(DBHelper.TASKS_COL_CATEGORY);
            if(!pry_filters.contains(pry))
                pry_filters.add(pry);
            if(!cat_filters.contains(cat)) {
                cat_filters.add(cat);
            }
        }

        priorityFilter.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, pry_filters));
        categoryFilter.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, cat_filters));

        priorityFilter.setOnItemSelectedListener(this);
        categoryFilter.setOnItemSelectedListener(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(addTask);
            }
        });
        /**
         * REMOVE BUTTON TO REMOVE THE TASK FROM THE TASK LIST
         */

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.remove(btn_remove.getRootView().findViewById(R.id.taskName).toString());
            }
        });

        setRecyclerView(taskList);

        return view;
    }

    ArrayList<HashMap<String, String>> categoryTasks;
    ArrayList<HashMap<String, String>> priorityTasks;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            ((TextView) view).setTextColor(Color.GRAY);

            // debug
//            Log.i("View Position", "Spinner position chose " + position);
        } else {
                // debug
//            Log.i("View Position", "Spinner position chose " + position);
//            Log.i("View ID", "View id = " + view.getId());
//            Log.i("Parent ID", "Parent id = " + parent.getId());
//            Log.i("Reg ID", "Reg id = " + id);
//            Log.i("Cat ID", "Category ID = " + categoryFilter.getId());
//            Log.i("R Cat ID", "Resource Category ID = " + R.id.spnCategoryFilter);
//            Log.i("Pry ID", "Priority ID = " + priorityFilter.getId());
//            Log.i("R Pry ID", "Resource Priority ID = " + R.id.spnPriorityFilter);

            // refreshes the recycler view with the appropriate filter
            switch(parent.getId()) {
                case R.id.spnCategoryFilter:
                    // debug
//                    Log.i("Category Filter", "Category spinner chose " + parent.getSelectedItem());
                    priorityFilter.setSelection(0);
                    categoryTasks = db.getCategoryTasks(Login.USER_ID, parent.getSelectedItem().toString());
                    setRecyclerView(categoryTasks);
                    break;
                case R.id.spnPriorityFilter:
                    // debug
//                    Log.i("Priority Filter", "Priority spinner chose " + parent.getSelectedItem());
                    categoryFilter.setSelection(0);
                    priorityTasks = db.getPriorityTasks(Login.USER_ID, Integer.parseInt(parent.getSelectedItem().toString()));
                    setRecyclerView(priorityTasks);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void setRecyclerView(ArrayList<HashMap<String, String>> taskList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new CustomTaskAdapter(getActivity(), taskList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}