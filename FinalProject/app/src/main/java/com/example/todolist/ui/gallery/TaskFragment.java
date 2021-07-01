package com.example.todolist.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment {

    CustomTaskAdapter adapter;
    ArrayList<HashMap<String,String>> data;
    private java.util.ArrayList<HashMap<String, String>> ArrayList;
    private FloatingActionButton add;
    private Button btn_remove;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_task, container,false);
       add = view.findViewById(R.id.btnAddTask);
       btn_remove = view.findViewById(R.id.btnRemoveTask);
       recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);

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
//
//        btn_remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        /** THIS CODE RIGHT HERE SIR, THIS IS CAUSING ALL THE TROUBLE, I THINK... */
        DBHelper db = new DBHelper(getContext());

//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
        adapter = new CustomTaskAdapter(getActivity(), db.getUserTasks(Login.USER_ID));   // error starts here
        recyclerView.setAdapter(adapter);

        // debug
        Log.i("RV Shown?", "" + recyclerView.isShown());

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

//        DBHelper db = new DBHelper(getContext());
//
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setHasFixedSize(true);
//        adapter = new CustomTaskAdapter(view.getContext(), db.getUserTasks(Login.USER_ID));
//        recyclerView.setAdapter(adapter);

//        mParentActivity.getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frag_task, frg)
//                .addToBackStack(null)
//                .commit();
    }

}